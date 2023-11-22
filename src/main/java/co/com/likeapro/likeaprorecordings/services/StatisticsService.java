package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Statistics;
import co.com.likeapro.likeaprorecordings.repositories.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
    private final StatisticsRepository statisticsRepository;

    public Mono<Statistics> saveStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics)
                .onErrorResume(throwable -> {
                    logger.error("Error saving the statistics.", throwable);
                    return Mono.empty();
                });
    }

    public Mono<Statistics> findStatisticsById(Long id) {
        return statisticsRepository.findById(id)
                .onErrorResume(throwable -> {
                    logger.error("Error finding the statistics with ID: .{}", id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Statistics with ID: " + id + " not found.").getMostSpecificCause()));
    }

    public Flux<Statistics> findAllStatistics() {
        return statisticsRepository.findAll()
                .onErrorResume(throwable -> {
                    logger.error("Error finding all statistics.", throwable);
                    return Flux.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Statistics not found.").getMostSpecificCause()));
    }

    public Mono<Statistics> updateStatistics(Long id, Statistics statistics) {
        return this.findStatisticsById(id)
                .flatMap(existingStatistics -> {
                    existingStatistics.setTimestamp(statistics.getTimestamp());
                    existingStatistics.setRecording(statistics.getRecording());
                    existingStatistics.setData(statistics.getData());
                    return statisticsRepository.save(existingStatistics);
                })
                .onErrorResume(throwable -> {
                    logger.error("Error updating the statistics with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteStatistics(Long id) {
        return this.findStatisticsById(id)
                .flatMap(statisticsRepository::delete)
                .onErrorResume(throwable -> {
                    logger.error("Error deleting the statistics with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }
}

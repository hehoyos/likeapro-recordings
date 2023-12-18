package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.config.KafkaConfig;
import co.com.likeapro.likeaprorecordings.models.BestEventInterface;
import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.models.MvpInterface;
import co.com.likeapro.likeaprorecordings.models.Recording;
import co.com.likeapro.likeaprorecordings.models.Statistics;
import co.com.likeapro.likeaprorecordings.repositories.StatisticsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class StatisticsService implements MvpInterface, BestEventInterface {

    private final Logger logger = LoggerFactory.getLogger(StatisticsService.class);
    private final StatisticsRepository statisticsRepository;
    private final CustomerService customerService;
    private final RecordingService recordingService;
    private final EventService eventService;
    private final KafkaTemplate<String, String> kafkaTemplate;

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

    @Override
    public Mono<Customer> getMvp() {
        return this.getStatisticsWithMvp()
                .flatMap(statistics -> customerService.findCustomerById(statistics.getData()))
                .onErrorResume(throwable -> {
                    logger.error("Error finding the most highlights customer.", throwable);
                    return Mono.empty();
                });
    }

    private Mono<Statistics> getStatisticsWithMvp() {
        return this.statisticsRepository.findMvp()
                .onErrorResume(throwable -> {
                    logger.error("Error finding the most highlights customer.", throwable);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Event> getBestEvent() {
        return this.getStatisticsWithBestEvent()
                .flatMap(statistics -> {
                    Mono<Recording> recording = recordingService.findRecordingById(statistics.getRecording());
                    return recording.flatMap(recordingMap -> eventService.findEventById(recordingMap.getEvent()));
                })
                .onErrorResume(throwable -> {
                    logger.error("Error finding the best event.", throwable);
                    return Mono.empty();
                });
    }

    private Mono<Statistics> getStatisticsWithBestEvent() {
        return this.statisticsRepository.findBestEvent()
                .onErrorResume(throwable -> {
                    logger.error("Error finding the best event.", throwable);
                    return Mono.empty();
                });
    }

    public Mono<Statistics> getStatisticsFromKafka(String topic, Integer partition, Integer offset)
            throws JsonProcessingException {

        ConsumerRecord<String, String> receivedStatistics;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        receivedStatistics = kafkaTemplate.receive(topic, partition, offset);
        ObjectMapper objectMapper = new ObjectMapper();
        Statistics statistics = objectMapper.readValue(Objects.requireNonNull(receivedStatistics).value(),
                Statistics.class);
        return this.saveStatistics(statistics);
    }
}

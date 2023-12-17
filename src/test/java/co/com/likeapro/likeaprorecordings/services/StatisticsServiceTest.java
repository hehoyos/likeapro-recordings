package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.models.Statistics;
import co.com.likeapro.likeaprorecordings.repositories.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;
    @InjectMocks
    private StatisticsService statisticsService;

    private Statistics statistics;
    private Mono<Statistics> expectedStatistics;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        statistics = new Statistics(1L, Timestamp.valueOf("2023-01-01 00:30:00"), 1L, 1L, now, now);
        expectedStatistics = Mono.just(statistics);
    }

    @Test
    void saveStatisticsTest() {
        when(statisticsRepository.save(statistics)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsService.saveStatistics(statistics);
        result.subscribe();

        verify(statisticsRepository).save(statistics);
    }

    @Test
    void findStatisticsByIdTest() {
        when(statisticsRepository.findById(1L)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsService.findStatisticsById(1L);
        result.subscribe();

        verify(statisticsRepository).findById(1L);
    }

    @Test
    void findAllStatisticsTest() {
        Flux<Statistics> expectedStatistics = Flux.just(statistics, statistics);
        when(statisticsRepository.findAll()).thenReturn(expectedStatistics);

        Flux<Statistics> result = statisticsService.findAllStatistics();
        result.subscribe();

        verify(statisticsRepository).findAll();
    }

    @Test
    void updateStatisticsTest() {
        when(statisticsRepository.findById(1L)).thenReturn(expectedStatistics);
        when(statisticsRepository.save(statistics)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsService.updateStatistics(1L, statistics);
        result.subscribe();

        verify(statisticsRepository).save(statistics);
    }

    @Test
    void deleteStatisticsByIdTest() {
        when(statisticsRepository.findById(1L)).thenReturn(expectedStatistics);
        when(statisticsRepository.delete(statistics)).thenReturn(Mono.empty());

        Mono<Void> result = statisticsService.deleteStatistics(1L);
        result.subscribe();

        verify(statisticsRepository).delete(statistics);
    }

    @Test
    void getMvpTest() {
        when(statisticsRepository.findMvp()).thenReturn(expectedStatistics);

        Mono<Customer> result = statisticsService.getMvp();
        result.subscribe();

        verify(statisticsRepository).findMvp();
    }

    @Test
    void getBestEventTest() {
        when(statisticsRepository.findBestEvent()).thenReturn(expectedStatistics);

        Mono<Event> result = statisticsService.getBestEvent();
        result.subscribe();

        verify(statisticsRepository).findBestEvent();
    }
}

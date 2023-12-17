package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Customer;
import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.models.Statistics;
import co.com.likeapro.likeaprorecordings.services.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private StatisticsController statisticsController;

    private Statistics statistics;
    private Mono<Statistics> expectedStatistics;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        statistics = new Statistics(1L, Timestamp.valueOf("2023-01-01 00:30:00"), 1L, 1L, now, now);
        expectedStatistics = Mono.just(statistics);
    }

    @Test
    void createStatisticsTest() {
        when(statisticsService.saveStatistics(statistics)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsController.createStatistics(statistics);
        result.subscribe();

        assertEquals(expectedStatistics, result);
    }

    @Test
    void readAllStatisticsTest() {
        Flux<Statistics> expectedStatistics = Flux.just(statistics, statistics);
        when(statisticsService.findAllStatistics()).thenReturn(expectedStatistics);

        Flux<Statistics> result = statisticsController.readAllStatistics();
        result.subscribe();

        assertEquals(expectedStatistics, result);
    }

    @Test
    void readStatisticsTest() {
        when(statisticsService.findStatisticsById(1L)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsController.readStatistics(1L);
        result.subscribe();

        assertEquals(expectedStatistics, result);
    }

    @Test
    void updateStatisticsTest() {
        when(statisticsService.findStatisticsById(1L)).thenReturn(expectedStatistics);
        when(statisticsService.updateStatistics(1L, statistics)).thenReturn(expectedStatistics);

        Mono<Statistics> result = statisticsController.updateStatistics(1L, statistics);
        result.subscribe();

        assertEquals(expectedStatistics, result);
    }

    @Test
    void deleteStatisticsTest() {
        when(statisticsService.findStatisticsById(1L)).thenReturn(expectedStatistics);
        when(statisticsService.deleteStatistics(1L)).thenReturn(Mono.empty());

        Mono<Void> result = statisticsController.deleteStatistics(1L);
        result.subscribe();

        assertEquals(Mono.empty(), result);
    }

    @Test
    void getMvpTest() {
        LocalDateTime now = LocalDateTime.now();
        Customer customer = new Customer(1L, "John Smith", "johnsmith1@example.com", "password1", "1234567890",
                "role1", true, now, now);
        Mono<Customer> expectedCustomer = Mono.just(customer);
        when(statisticsService.getMvp()).thenReturn(expectedCustomer);

        Mono<Customer> result = statisticsController.getMvp();
        result.subscribe();

        assertEquals(expectedCustomer, result);
    }

    @Test
    void getBestEventTest() {
        LocalDateTime now = LocalDateTime.now();
        Event event = new Event(1L, "Partido de Fútbol: Medellín vs Nacional", "Partido de la liga de fútbol", now,
                true, "John Smith, Jane Doe", now, now);
        Mono<Event> expectedEvent = Mono.just(event);
        when(statisticsService.getBestEvent()).thenReturn(expectedEvent);

        Mono<Event> result = statisticsController.getBestEvent();
        result.subscribe();

        assertEquals(expectedEvent, result);
    }
}

package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EventControllerTest {

    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;

    private Event event;
    private Mono<Event> expectedEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        event = new Event(1L, "Partido de Fútbol: Medellín vs Nacional", "Partido de la liga de fútbol", now,
                true, "John Smith, Jane Doe", now, now);
        expectedEvent = Mono.just(event);
    }

    @Test
    void createEventTest() {
        when(eventService.saveEvent(event)).thenReturn(expectedEvent);

        Mono<Event> result = eventController.createEvent(event);
        result.subscribe();

        assertEquals(expectedEvent, result);
    }

    @Test
    void readAllEventsTest() {
        Flux<Event> expectedEvents = Flux.just(event, event);
        when(eventService.findAllEvents()).thenReturn(expectedEvents);

        Flux<Event> result = eventController.readAllEvents();
        result.subscribe();

        assertEquals(expectedEvents, result);
    }

    @Test
    void readEventTest() {
        when(eventService.findEventById(1L)).thenReturn(expectedEvent);

        Mono<Event> result = eventController.readEvent(1L);
        result.subscribe();

        assertEquals(expectedEvent, result);
    }

    @Test
    void updateEventTest() {
        when(eventService.findEventById(1L)).thenReturn(expectedEvent);
        when(eventService.updateEvent(1L, event)).thenReturn(expectedEvent);

        Mono<Event> result = eventController.updateEvent(1L, event);
        result.subscribe();

        assertEquals(expectedEvent, result);
    }

    @Test
    void deleteEventTest() {
        when(eventService.findEventById(1L)).thenReturn(expectedEvent);
        when(eventService.deleteEvent(1L)).thenReturn(Mono.empty());

        Mono<Void> result = eventController.deleteEvent(1L);
        result.subscribe();

        assertEquals(Mono.empty(), result);
    }
}

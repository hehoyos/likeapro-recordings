package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;

    private Event event;
    private Mono<Event> expectedEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        event = new Event(1L, "Partido de Fútbol: Medellín vs Nacional", "Partido de la liga de fútbol", now, true,
                "John Smith, Jane Doe", now, now);
        expectedEvent = Mono.just(event);
    }

    @Test
    void saveEventTest() {
        when(eventRepository.save(event)).thenReturn(expectedEvent);

        Mono<Event> result = eventService.saveEvent(event);
        result.subscribe();

        verify(eventRepository).save(event);
    }

    @Test
    void findEventByIdTest() {
        when(eventRepository.findById(1L)).thenReturn(expectedEvent);

        Mono<Event> result = eventService.findEventById(1L);
        result.subscribe();

        verify(eventRepository).findById(1L);
    }

    @Test
    void findAllEventsTest() {
        Flux<Event> expectedEvents = Flux.just(event, event);
        when(eventRepository.findAll()).thenReturn(expectedEvents);

        Flux<Event> result = eventService.findAllEvents();
        result.subscribe();

        verify(eventRepository).findAll();
    }

    @Test
    void updateEventTest() {
        when(eventRepository.findById(1L)).thenReturn(expectedEvent);
        when(eventRepository.save(event)).thenReturn(expectedEvent);

        Mono<Event> result = eventService.updateEvent(1L, event);
        result.subscribe();

        verify(eventRepository).save(event);
    }

    @Test
    void deleteEventTest() {
        when(eventRepository.findById(1L)).thenReturn(expectedEvent);
        when(eventRepository.delete(event)).thenReturn(Mono.empty());

        Mono<Void> result = eventService.deleteEvent(1L);
        result.subscribe();

        verify(eventRepository).delete(event);
    }
}

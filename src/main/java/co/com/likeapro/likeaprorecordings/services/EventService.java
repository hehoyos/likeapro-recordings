package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.repositories.EventRepository;
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
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);
    private final EventRepository eventRepository;

    public Mono<Event> saveEvent(Event event) {
        return eventRepository.save(event)
                .onErrorResume(throwable -> {
                    logger.error("Error saving the event.", throwable);
                    return Mono.empty();
                });
    }

    public Mono<Event> findEventById(Long id) {
        return eventRepository.findById(id)
                .onErrorResume(throwable -> {
                    logger.error("Error finding the event with ID: .{}", id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Event with ID: " + id + " not found.").getMostSpecificCause()));
    }

    public Flux<Event> findAllEvents() {
        return eventRepository.findAll()
                .onErrorResume(throwable -> {
                    logger.error("Error finding all events.", throwable);
                    return Flux.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Events not found.").getMostSpecificCause()));
    }

    public Mono<Event> updateEvent(Long id, Event event) {
        return this.findEventById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(event.getName());
                    existingCustomer.setDescription(event.getDescription());
                    existingCustomer.setDate(event.getDate());
                    existingCustomer.setStatus(event.getStatus());
                    existingCustomer.setCustomers(event.getCustomers());
                    return eventRepository.save(existingCustomer);
                })
                .onErrorResume(throwable -> {
                    logger.error("Error updating the event with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteEvent(Long id) {
        return this.findEventById(id)
                .flatMap(eventRepository::delete)
                .onErrorResume(throwable -> {
                    logger.error("Error deleting the event with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }
}

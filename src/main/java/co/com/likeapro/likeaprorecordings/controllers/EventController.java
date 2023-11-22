package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Event;
import co.com.likeapro.likeaprorecordings.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @PostMapping("/")
    public Mono<Event> createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/{id}")
    public Mono<Event> readEvent(@PathVariable Long id) {
        return eventService.findEventById(id);
    }

    @GetMapping("/")
    public Flux<Event> readAllEvents() {
        return eventService.findAllEvents();
    }

    @PutMapping("/{id}")
    public Mono<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}

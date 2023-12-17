package co.com.likeapro.likeaprorecordings.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {

    private Event event;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        event = new Event(1L, "Partido de Fútbol: Medellín vs Nacional", "Partido de la liga de fútbol", now, true,
                "John Smith, Jane Doe", now, now);
        event.setId(1L);
    }

    @Test
    void getId() {
        assertEquals(1L, event.getId());
    }

    @Test
    void getName() {
        assertEquals("Partido de Fútbol: Medellín vs Nacional", event.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Partido de la liga de fútbol", event.getDescription());
    }

    @Test
    void getDate() {
        assertEquals(now, event.getDate());
    }

    @Test
    void getStatus() {
        assertTrue(event.getStatus());
    }

    @Test
    void getCustomers() {
        assertEquals("John Smith, Jane Doe", event.getCustomers());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, event.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, event.getUpdatedAt());
    }
}

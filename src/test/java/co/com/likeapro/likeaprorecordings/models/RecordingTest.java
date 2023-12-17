package co.com.likeapro.likeaprorecordings.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecordingTest {

    private Recording recording;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        recording = new Recording(1L, "GPF2023-01-01T00:00:00", 1L, Time.valueOf("01:30:00"), true, now, now);
        recording.setId(1L);
    }

    @Test
    void getId() {
        assertEquals(1L, recording.getId());
    }

    @Test
    void getName() {
        assertEquals("GPF2023-01-01T00:00:00", recording.getName());
    }

    @Test
    void getEvent() {
        assertEquals(1L, recording.getEvent());
    }

    @Test
    void getDuration() {
        assertEquals(Time.valueOf("01:30:00"), recording.getDuration());
    }

    @Test
    void getStatus() {
        assertTrue(recording.getStatus());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, recording.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, recording.getUpdatedAt());
    }
}

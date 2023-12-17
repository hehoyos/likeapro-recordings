package co.com.likeapro.likeaprorecordings.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsTest {

    private Statistics statistics;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        statistics = new Statistics(1L, Timestamp.valueOf("2023-01-01 00:30:00"), 1L, 1L, now, now);
        statistics.setId(1L);
    }

    @Test
    void getId() {
        assertEquals(1L, statistics.getId());
    }

    @Test
    void getTimestamp() {
        assertEquals(Timestamp.valueOf("2023-01-01 00:30:00"), statistics.getTimestamp());
    }

    @Test
    void getRecording() {
        assertEquals(1L, statistics.getRecording());
    }

    @Test
    void getData() {
        assertEquals(1L, statistics.getData());
    }

    @Test
    void getCreatedAt() {
        assertEquals(now, statistics.getCreatedAt());
    }

    @Test
    void getUpdatedAt() {
        assertEquals(now, statistics.getUpdatedAt());
    }
}

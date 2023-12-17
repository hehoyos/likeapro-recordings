package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Recording;
import co.com.likeapro.likeaprorecordings.services.RecordingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecordingControllerTest {

    @Mock
    private RecordingService recordingService;
    @InjectMocks
    private RecordingController recordingController;

    private Recording recording;
    private Mono<Recording> expectedRecording;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        recording = new Recording(1L, "GPF2023-01-01T00:00:00", 1L, Time.valueOf("01:30:00"), true, now, now);
        expectedRecording = Mono.just(recording);
    }

    @Test
    void createRecordingTest() {
        when(recordingService.saveRecording(recording)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingController.createRecording(recording);
        result.subscribe();

        assertEquals(expectedRecording, result);
    }

    @Test
    void readAllRecordingTest() {
        Flux<Recording> expectedRecordings = Flux.just(recording, recording);
        when(recordingService.findAllRecordings()).thenReturn(expectedRecordings);

        Flux<Recording> result = recordingController.readAllRecordings();
        result.subscribe();

        assertEquals(expectedRecordings, result);
    }

    @Test
    void readRecordingTest() {
        when(recordingService.findRecordingById(1L)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingController.readRecording(1L);
        result.subscribe();

        assertEquals(expectedRecording, result);
    }

    @Test
    void updateRecordingTest() {
        when(recordingService.findRecordingById(1L)).thenReturn(expectedRecording);
        when(recordingService.updateRecording(1L, recording)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingController.updateRecording(1L, recording);
        result.subscribe();

        assertEquals(expectedRecording, result);
    }

    @Test
    void deleteRecordingTest() {
        when(recordingService.findRecordingById(1L)).thenReturn(expectedRecording);
        when(recordingService.deleteRecording(1L)).thenReturn(Mono.empty());

        Mono<Void> result = recordingController.deleteRecording(1L);
        result.subscribe();

        assertEquals(Mono.empty(), result);
    }
}

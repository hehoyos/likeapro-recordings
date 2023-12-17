package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.models.Recording;
import co.com.likeapro.likeaprorecordings.repositories.RecordingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecordingServiceTest {

    @Mock
    private RecordingRepository recordingRepository;
    @InjectMocks
    private RecordingService recordingService;

    private Recording recording;
    private Mono<Recording> expectedRecording;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime now = LocalDateTime.now();
        recording = new Recording(1L, "GPF2023-01-01T00:00:00", 1L, Time.valueOf("01:30:00"), true, now, now);
        expectedRecording = Mono.just(recording);
    }

    @Test
    void saveRecordingTest() {
        when(recordingRepository.save(recording)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingService.saveRecording(recording);
        result.subscribe();

        verify(recordingRepository).save(recording);
    }

    @Test
    void findRecordingByIdTest() {
        when(recordingRepository.findById(1L)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingService.findRecordingById(1L);
        result.subscribe();

        verify(recordingRepository).findById(1L);
    }

    @Test
    void findAllRecordingsTest() {
        Flux<Recording> expectedRecordings = Flux.just(recording, recording);
        when(recordingRepository.findAll()).thenReturn(expectedRecordings);

        Flux<Recording> result = recordingService.findAllRecordings();
        result.subscribe();

        verify(recordingRepository).findAll();
    }

    @Test
    void updateRecordingTest() {
        when(recordingRepository.findById(1L)).thenReturn(expectedRecording);
        when(recordingRepository.save(recording)).thenReturn(expectedRecording);

        Mono<Recording> result = recordingService.updateRecording(1L, recording);
        result.subscribe();

        verify(recordingRepository).save(recording);
    }

    @Test
    void deleteRecordingByIdTest() {
        when(recordingRepository.findById(1L)).thenReturn(expectedRecording);
        when(recordingRepository.delete(recording)).thenReturn(Mono.empty());

        Mono<Void> result = recordingService.deleteRecording(1L);
        result.subscribe();

        verify(recordingRepository).delete(recording);
    }
}

package co.com.likeapro.likeaprorecordings.services;

import co.com.likeapro.likeaprorecordings.config.KafkaConfig;
import co.com.likeapro.likeaprorecordings.models.Recording;
import co.com.likeapro.likeaprorecordings.repositories.RecordingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RecordingService {

    private final Logger logger = LoggerFactory.getLogger(RecordingService.class);
    private final RecordingRepository recordingRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Mono<Recording> saveRecording(Recording recording) {
        return recordingRepository.save(recording)
                .onErrorResume(throwable -> {
                    logger.error("Error saving the recording.", throwable);
                    return Mono.empty();
                });
    }

    public Mono<Recording> findRecordingById(Long id) {
        return recordingRepository.findById(id)
                .onErrorResume(throwable -> {
                    logger.error("Error finding the recording with ID: .{}", id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Recording with ID: " + id + " not found.").getMostSpecificCause()));
    }

    public Flux<Recording> findAllRecordings() {
        return recordingRepository.findAll()
                .onErrorResume(throwable -> {
                    logger.error("Error finding all recordings.", throwable);
                    return Flux.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Recordings not found.").getMostSpecificCause()));
    }

    public Mono<Recording> updateRecording(Long id, Recording recording) {
        return this.findRecordingById(id)
                .flatMap(existingRecording -> {
                    existingRecording.setName(recording.getName());
                    existingRecording.setEvent(recording.getEvent());
                    existingRecording.setDuration(recording.getDuration());
                    existingRecording.setStatus(recording.getStatus());
                    return recordingRepository.save(existingRecording);
                })
                .onErrorResume(throwable -> {
                    logger.error("Error updating the recording with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteRecording(Long id) {
        return this.findRecordingById(id)
                .flatMap(recordingRepository::delete)
                .onErrorResume(throwable -> {
                    logger.error("Error deleting the recording with ID: .{}", id, throwable);
                    return Mono.empty();
                });
    }

    public Mono<Recording> getRecordingFromKafka(String topic, Integer partition, Integer offset)
            throws JsonProcessingException {

        ConsumerRecord<String, String> receivedRecording;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        receivedRecording = kafkaTemplate.receive(topic, partition, offset);
        ObjectMapper objectMapper = new ObjectMapper();
        Recording recording = objectMapper.readValue(Objects.requireNonNull(receivedRecording).value(),
                Recording.class);
        return this.saveRecording(recording);
    }
}

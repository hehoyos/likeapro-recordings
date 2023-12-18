package co.com.likeapro.likeaprorecordings.controllers;

import co.com.likeapro.likeaprorecordings.models.Recording;
import co.com.likeapro.likeaprorecordings.services.RecordingService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping("/recording")
@AllArgsConstructor
public class RecordingController {
    
    private RecordingService recordingService;

    @PostMapping("/")
    public Mono<Recording> createRecording(@RequestBody Recording recording) {
        return recordingService.saveRecording(recording);
    }

    @GetMapping("/{id}")
    public Mono<Recording> readRecording(@PathVariable Long id) {
        return recordingService.findRecordingById(id);
    }

    @GetMapping("/")
    public Flux<Recording> readAllRecordings() {
        return recordingService.findAllRecordings();
    }

    @PutMapping("/{id}")
    public Mono<Recording> updateRecording(@PathVariable Long id, @RequestBody Recording recording) {
        return recordingService.updateRecording(id, recording);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteRecording(@PathVariable Long id) {
        return recordingService.deleteRecording(id);
    }

    @GetMapping("/kafka/{topic}/{partition}/{offset}")
    public Mono<Recording> getRecordingFromKafkaTopic(@PathVariable String topic, @PathVariable Integer partition,
                                              @PathVariable Integer offset) throws JsonProcessingException {
        return recordingService.getRecordingFromKafka(topic, partition, offset);
    }
}

package co.com.likeapro.likeaprorecordings.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {

    @Id
    private Long id;
    private Timestamp timestamp;
    private Long recording;
    private Long data;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package co.com.likeapro.likeaprorecordings.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

    @Id
    private Long id;
    private String name;
    private Long event;
    private Time duration;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

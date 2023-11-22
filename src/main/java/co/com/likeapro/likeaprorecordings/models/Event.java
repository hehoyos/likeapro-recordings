package co.com.likeapro.likeaprorecordings.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Event {

    @Id
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Boolean status;
    private String customers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

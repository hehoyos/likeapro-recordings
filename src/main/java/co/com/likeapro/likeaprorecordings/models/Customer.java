package co.com.likeapro.likeaprorecordings.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

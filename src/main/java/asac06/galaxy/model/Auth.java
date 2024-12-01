package asac06.galaxy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String refresh;
    private LocalDateTime expiration;
//    private Boolean isActive;

    public Auth(String username, String refresh, LocalDateTime expiration) {
        this.username = username;
        this.refresh = refresh;
        this.expiration = expiration;
    }
}

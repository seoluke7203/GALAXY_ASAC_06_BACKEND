package asac06.galaxy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    Long id; //placeholder
    String name;
    String login_id;
    String password;

    @Column(nullable = false, unique = true)
    String email;
}

package asac06.galaxy.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
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
    @Column(nullable = false, unique = true)
    String username;
    String password;

    @Column(nullable = false, unique = true)
    String email;

//    @Enumerated(EnumType.STRING)
    UserRole role = UserRole.USER;

    public enum UserRole {
        ADMIN, USER;
    }
}

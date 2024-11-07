package asac06.galaxy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Venue {
    @Id @GeneratedValue
    @Column(name = "venue_id")
    private Long id;

    private String name;
    private String location;
}

package asac06.galaxy.model;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id @GeneratedValue
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @Enumerated
    private SeatType type;

    private String zone;
}

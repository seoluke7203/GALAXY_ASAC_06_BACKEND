package asac06.galaxy.model;

import jakarta.persistence.*;

@Entity
public class ScheduleSeat {
    @Id @GeneratedValue
    @Column(name = "schedule_seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private ScheduleSeatStatus status;
}

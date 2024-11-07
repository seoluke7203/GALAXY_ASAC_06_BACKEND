package asac06.galaxy.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String title;
    private LocalDateTime releaseDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private int runningTime;
    private int price;
    private String place;

    @Enumerated(EnumType.STRING)
    private ProductGenre genre;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private String content;
    private String posterSrc;
    private String posterDetail;
}

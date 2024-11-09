package asac06.galaxy.repository;


import static asac06.galaxy.exception.ExceptionType.PRODUCT_NOT_FOUND;
import static java.util.Arrays.asList;

import asac06.galaxy.exception.CustomException;
import asac06.galaxy.model.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.Popup;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class TicketingRepository {

    public static List<Product> products = asList(
        Product.builder()
            .id(1L)
            .title("1번 공연")
            .place("광림아트센터 BBCH홀")
            .runningTime(125)
            .price(60000)
            .startDate(LocalDate.of(2010, 10, 24))
            .endDate(LocalDate.of(2010, 10, 30))
            .posterSrc("1번 src")
            .build()
    ,
        Product.builder()
            .id(2L)
            .title("2번 공연")
            .place("서울 월드컵 경기장")
            .runningTime(1250)
            .price(70000)
            .startDate(LocalDate.of(2011, 11, 24))
            .endDate(LocalDate.of(2011, 11, 30))
            .posterSrc("2번 src")
            .build()
    ,
        Product.builder()
            .id(3L)
            .title("3번 공연")
            .place("용인 월드컵 경기장")
            .runningTime(12500)
            .price(80000)
            .startDate(LocalDate.of(2012, 12, 24))
            .endDate(LocalDate.of(2012, 12, 30))
            .posterSrc("3번 src")
            .build()
    );

    public Product findProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id))
            .findFirst().orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND, id));
    }

}

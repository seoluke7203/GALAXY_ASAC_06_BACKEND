package asac06.galaxy;

import asac06.galaxy.model.Product;
import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.model.ProductType;
import asac06.galaxy.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.productInit();
//        initService.userInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
//        private final PasswordEncoder passwordEncoder;

//        public void userInit() {
//            User user = new User();
//            user.setId(1L);
//            user.setLoginId("test@gmail.com");
//            user.setEmail("test@gmail.com");
//            user.setPassword(passwordEncoder.encode("12345"));
//            em.persist(user);
//        }


        @RequiredArgsConstructor
        public enum Genre {
            MUSICAL(ProductGenre.MUSICAL,"/src/assets/images/posters/jesuschristPoster.jpeg", "/src/assets/images/postersDetail/jesusMainPic.jpg"),
            CONCERT(ProductGenre.CONCERT,"/src/assets/images/posters/stillJypPoster.gif", "/src/assets/images/postersDetail/stillJypDetail.jpg"),
            THEATER(ProductGenre.THEATER,"/src/assets/images/posters/aladinPoster.png", "/src/assets/images/postersDetail/performace1.jpg");

            private final ProductGenre genre;
            private final String productSrc;
            private final String productDetail;
        }

        public void productInit() {
            LocalDateTime today = LocalDateTime.now();
            today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss"));

            LocalDateTime releaseDate = today.minusDays(3);
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(10);

            String title = "test_";
            StringBuilder sb = new StringBuilder(title);


            for(Genre genreItem : Genre.values()) {
                for (int i = 1; i <= 5; i++) {
                    title = sb.replace(4,5, String.valueOf(i)).toString();
                    Product product = createProduct(title, releaseDate.plusDays(Long.valueOf(i)), startDate.plusDays(i), endDate.plusDays(i), genreItem);
                    em.persist(product);
                }
            }
        }

        private static Product createProduct(String title, LocalDateTime releaseDate, LocalDate startDate, LocalDate endDate, Genre genreItem /*, ProductGenre genre,  ProductType productType, String posterSrc, String posterDetail */) {
            Product product = new Product();
            product.setTitle(title);
            product.setReleaseDate(releaseDate);
            product.setStartDate(startDate);
            product.setEndDate(endDate);
            product.setRunningTime(120);
            product.setPrice(10000);
            product.setPlace("예술의 전당");
            product.setGenre(genreItem.genre);
            product.setProductType(ProductType.SCHEDULE);
            product.setContent("테스트입니다. 테스트입니다. 테스트입니다. 테스트입니다. 테스트입니다.");
            product.setPosterSrc(genreItem.productSrc);
            product.setPosterDetail(genreItem.productDetail);
            return product;
        }
    }
}

package asac06.galaxy.repository.dto;

import asac06.galaxy.model.ProductType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;

@Data
public class ProductDto {

    @Data
    public static class MainPosterProductDto {
        private Long productId;
        private String title;
        private String place;
        private LocalDate startDate;
        private LocalDate endDate;
        private String productSrc;

        public MainPosterProductDto(Long productId, String title, String place, LocalDate startDate, LocalDate endDate, String productSrc) {
            this.productId = productId;
            this.title = title;
            this.place = place;
            this.startDate = startDate;
            this.endDate = endDate;
            this.productSrc = productSrc;
        }
    }

    @Data
    public static class SubPosterProductDto {
        private Long productId;
        private String title;
        private String content;
        private String releaseDate;
        private String productSrc;

        public SubPosterProductDto(Long productId, String title, String content, LocalDateTime releaseDate, String productSrc) {
            this.productId = productId;
            this.title = title;
            this.content = content;
            this.releaseDate = releaseDate.format(dateTimeFormatter);
            this.productSrc = productSrc;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd HH:mm (E)");
    }

    @Data
    public static class ProductDetailDto {
        private String title;
        private LocalDateTime releaseDate;
        private LocalDate startDate;
        private LocalDate endDate;
        private int runningTime;
        private int price;
        private String place;

        private String posterSrc;
        private String posterDetail;
        private ProductType productType;

        public ProductDetailDto(String title, LocalDateTime releaseDate, LocalDate startDate,
            LocalDate endDate, int runningTime, int price, String place, String posterSrc,
            String posterDetail) {
            this.title = title;
            this.releaseDate = releaseDate;
            this.startDate = startDate;
            this.endDate = endDate;
            this.runningTime = runningTime;
            this.price = price;
            this.place = place;
            this.posterSrc = posterSrc;
            this.posterDetail = posterDetail;
            this.productType = ProductType.SCHEDULE;
        }
    }
}

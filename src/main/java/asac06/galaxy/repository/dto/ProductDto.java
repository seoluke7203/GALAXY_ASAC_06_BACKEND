package asac06.galaxy.repository.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public static class SubProductDto {
        private Long productId;
        private String title;
        private String content;
        private LocalDateTime releaseDate;
        private String productSrc;

        public SubProductDto(Long productId, String title, String content, LocalDateTime releaseDate, String productSrc) {
            this.productId = productId;
            this.title = title;
            this.content = content;
            this.releaseDate = releaseDate;
            this.productSrc = productSrc;
        }
    }
}

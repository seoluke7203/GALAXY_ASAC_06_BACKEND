package asac06.galaxy.service;

import static asac06.galaxy.exception.ExceptionType.PRODUCT_NOT_FOUND;

import asac06.galaxy.exception.CustomException;
import asac06.galaxy.model.Product;
import asac06.galaxy.repository.TicketingRepository;
import asac06.galaxy.repository.dto.ProductDto;
import asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;
import asac06.galaxy.repository.dto.ProductDto.ProductDetailDto;
import asac06.galaxy.repository.dto.ResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketingService {

    private final TicketingRepository ticketingRepository;


    public ProductDetailDto findProductById(Long id) {
        Optional<Product> product = ticketingRepository.findById(id);
        Product searchProduct = product.orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND, id));

        return new ProductDetailDto(
            searchProduct.getTitle(),
            searchProduct.getReleaseDate(),
            searchProduct.getStartDate(),
            searchProduct.getEndDate(),
            searchProduct.getRunningTime(),
            searchProduct.getPrice(),
            searchProduct.getPlace(),
            searchProduct.getPosterSrc(),
            searchProduct.getPosterDetail()
        );
    }
}

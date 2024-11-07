package asac06.galaxy.service;

import asac06.galaxy.model.Product;
import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;

@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<MainPosterProductDto> findAllByGenre(ProductGenre genre) {

        log.info("findAll genre : " + genre );
        List<Product> products = productRepository.findAllByGenre(genre);

        List<MainPosterProductDto> productDtos = products.stream()
                .map(product -> new MainPosterProductDto(product.getId(), product.getTitle(), product.getPlace(), product.getStartDate(), product.getEndDate(), product.getPosterSrc()))
                .toList();
        return productDtos;
    }
}

package asac06.galaxy.service.impl;

import asac06.galaxy.model.Product;
import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.repository.ProductRepository;
import asac06.galaxy.repository.dto.ProductDto;
import asac06.galaxy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public List<ProductDto.MainPosterProductDto> findAllByGenre(ProductGenre genre) {

        List<Product> products = productRepository.findAllByGenre(genre);

        List<ProductDto.MainPosterProductDto> productDtos = products.stream()
                .map(product -> new ProductDto.MainPosterProductDto(product.getId(), product.getTitle(), product.getPlace(), product.getStartDate(), product.getEndDate(), product.getPosterSrc()))
                .toList();
        return productDtos;
    }

    @Override
    public List<ProductDto.MainPosterProductDto> findMainProducts(ProductGenre genre) {
        List<Product> products = productRepository.findMainProducts(genre);

        List<ProductDto.MainPosterProductDto> mainProduct = products.stream()
                .map(product -> new ProductDto.MainPosterProductDto(product.getId(), product.getTitle(), product.getPlace(), product.getStartDate(), product.getEndDate(), product.getPosterSrc()))
                .toList();

        return mainProduct;
    }

    @Override
    public List<ProductDto.SubPosterProductDto> findSubProducts(ProductGenre genre) {
        List<Product> products = productRepository.findSubProducts(genre);

        List<ProductDto.SubPosterProductDto> subProducts = products.stream()
                .map(product -> new ProductDto.SubPosterProductDto(product.getId(), product.getTitle(), product.getContent(), product.getReleaseDate(), product.getPosterSrc()))
                .toList();
        return subProducts;
    }
}

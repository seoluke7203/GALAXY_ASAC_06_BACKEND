package asac06.galaxy.service;

import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.repository.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto.MainPosterProductDto> findAllByGenre(ProductGenre genre);
    public List<ProductDto.MainPosterProductDto> findMainProducts(ProductGenre genre);
    public List<ProductDto.SubPosterProductDto> findSubProducts(ProductGenre genre);
}

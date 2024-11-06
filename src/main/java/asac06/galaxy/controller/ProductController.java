package asac06.galaxy.controller;

import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.repository.dto.ResponseDto;
import asac06.galaxy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // main banner
    @GetMapping("/products/{genre}")
    public ResponseDto<List<MainPosterProductDto>> mainProducts(@PathVariable("genre") String genre) {

        log.info("==================================================================");
        log.info("genre: " + genre);
        log.info("==================================================================");


        // 추후 수정
        ProductGenre productGenre = ProductGenre.of(String.valueOf(genre));

        List<MainPosterProductDto> resultList =  productService.findAllByGenre(productGenre);
        return new ResponseDto<>(HttpStatus.OK.value(), resultList);
    }
}

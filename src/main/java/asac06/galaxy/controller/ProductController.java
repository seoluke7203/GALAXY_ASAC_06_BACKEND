package asac06.galaxy.controller;

import asac06.galaxy.model.ProductGenre;
import asac06.galaxy.repository.dto.ProductDto;
import asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;
import asac06.galaxy.repository.dto.ProductDto.SubPosterProductDto;
import asac06.galaxy.repository.dto.ResponseDto;
import asac06.galaxy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // main banner
    @GetMapping("/main/{genre}")
    public ResponseEntity<ResponseDto<List<MainPosterProductDto>>> mainProducts(@PathVariable("genre") String genre) {

        // 추후 수정
        ProductGenre productGenre = ProductGenre.of(String.valueOf(genre));

        List<MainPosterProductDto> resultList =  productService.findMainProducts(productGenre);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseDto<>(200, resultList));
    }

    @GetMapping("/sub/{genre}")
    public ResponseEntity<ResponseDto<List<SubPosterProductDto>>> subProducts(@PathVariable("genre") String genre) {

        // 추후 수정
        ProductGenre productGenre = ProductGenre.of(String.valueOf(genre));

        List<SubPosterProductDto> resultList =  productService.findSubProducts(productGenre);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto<>(200, resultList));
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

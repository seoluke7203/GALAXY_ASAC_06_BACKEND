package asac06.galaxy.controller;

import asac06.galaxy.model.Product;
import asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;
import asac06.galaxy.repository.dto.ProductDto.ProductDetailDto;
import asac06.galaxy.repository.dto.ResponseDto;
import asac06.galaxy.service.TicketingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketingController {

    private final TicketingService ticketingService;

    @GetMapping("/ticketing/{id}")
    public ResponseDto<ProductDetailDto> productDetailElements(@PathVariable("id") Long id) {

        return new ResponseDto<ProductDetailDto>(HttpStatus.OK.value(), ticketingService.findProductById(id));
    }

}

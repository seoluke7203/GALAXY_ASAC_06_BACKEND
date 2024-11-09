package asac06.galaxy.service;

import asac06.galaxy.model.Product;
import asac06.galaxy.repository.TicketingRepository;
import asac06.galaxy.repository.dto.ProductDto;
import asac06.galaxy.repository.dto.ProductDto.MainPosterProductDto;
import asac06.galaxy.repository.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketingService {

    private final TicketingRepository ticketingRepository;


    public MainPosterProductDto findProductById(Long id) {

        Product product = ticketingRepository.findProductById(id);
        return new MainPosterProductDto(
            product.getId(),
            product.getTitle(),
            product.getPlace(),
            product.getStartDate(),
            product.getEndDate(),
            product.getPosterSrc()
        );
    }

}

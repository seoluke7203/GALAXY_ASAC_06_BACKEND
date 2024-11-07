package asac06.galaxy.repository;


import asac06.galaxy.model.Product;
import asac06.galaxy.model.ProductGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByGenre(ProductGenre genre);
}

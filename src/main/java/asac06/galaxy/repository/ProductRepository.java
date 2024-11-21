package asac06.galaxy.repository;


import asac06.galaxy.model.Product;
import asac06.galaxy.model.ProductGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByGenre(ProductGenre genre);

    @Query("select p from Product p where p.genre = :genre and date(p.releaseDate) <= CURRENT_DATE and CURRENT_DATE < date(p.endDate) ")
    List<Product> findMainProducts(@Param("genre") ProductGenre genre);

    @Query("select p from Product p where p.genre = :genre and date(p.releaseDate) > CURRENT_DATE")
    List<Product> findSubProducts(@Param("genre") ProductGenre genre);
}

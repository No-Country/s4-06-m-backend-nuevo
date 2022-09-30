package ecommerce.eco.repository;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   List<Product> findByShortDetailsOrDetailsOrTitle(String shortDetails, String details, String title);
}
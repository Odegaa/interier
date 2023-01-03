package uz.interier.repositories;

import uz.interier.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductsByProductName(String productName);

    Product getProductsByPrice(BigDecimal price);

}
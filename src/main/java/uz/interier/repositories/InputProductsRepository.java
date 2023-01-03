package uz.interier.repositories;

import uz.interier.models.InputProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputProductsRepository extends JpaRepository<InputProducts, Long> {

}
package uz.interier.repositories;

import uz.interier.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByCategoryName(String categoryName);
    Category findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

}
package app.repositories;

import app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *Category repository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT r FROM Category r WHERE r.nameCategory = :nameCategory")
    Category getCategoryByName(String name);
}

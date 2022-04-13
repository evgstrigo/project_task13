package app.repositories;

import app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий класса "Category". Нужен для операций сохранения в БД сущностей Category.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT r FROM Category r WHERE r.name = :name")
    Category getCategoryByName(String name);
}

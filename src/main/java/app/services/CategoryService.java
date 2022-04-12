package app.services;

import app.entities.Category;

import java.util.List;

/**
 * Интерфейс слоя сервиса класса Category.
 */
public interface CategoryService {

    void save(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> findAll();
    void delete(Long id);
}

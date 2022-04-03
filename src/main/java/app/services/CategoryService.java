package app.services;

import app.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *Category service
 */
public interface CategoryService {

    void saveCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategory(String category);
    List<Category> getAllCategory();
    void deleteCategoryById(Long id);
}

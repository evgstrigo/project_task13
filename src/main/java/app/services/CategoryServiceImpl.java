package app.services;

import app.entities.Category;
import app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс сервиса сущности Category. Выполняет CRUD операции.
 */

@Service
public class CategoryServiceImpl implements  CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Category getCategoryByName(String category) {
        return categoryRepository.getCategoryByName(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

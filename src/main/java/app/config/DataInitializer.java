package app.config;

import app.entities.Category;
import app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */
@Component
public class DataInitializer {

    public final CategoryService categoryService;

    @Autowired
    public DataInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");
    }

    /**
     *Creating categories in the database
     *You can add new ones here
     */
    @PostConstruct
    public void createCategory() {
        Category business = new Category("BUSINESS");
        Category economy = new Category("ECONOMY");

        categoryService.saveCategory(business);
        categoryService.saveCategory(economy);
    }
}

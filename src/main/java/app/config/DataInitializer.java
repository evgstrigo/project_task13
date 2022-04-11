package app.config;

import lombok.extern.log4j.Log4j2;
import app.entities.Category;
import app.services.CategoryService;
import app.services.PassengerService;
import app.util.PassengerAndPassportCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */
@Log4j2
@Component
public class DataInitializer {

    private final PassengerService passengerService;
    public final CategoryService categoryService;

    @Autowired
    public DataInitializer(PassengerService passengerService, CategoryService categoryService) {
        this.passengerService = passengerService;
        this.categoryService = categoryService;
    }


    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");
        log.info("Тест логгера");


        createCategory();
    }

    @PostConstruct
    public void addFivePassengersToDB() {
        PassengerAndPassportCreator.createFivePassengerAndSaveInDB(passengerService);
        log.info("Тест логгера");
    }

    /**
     * Метод создания и записи в БД новых сущностей класса Category.
     * При необходимости можно добавить новые категории здесь.
     */
    private void createCategory() {
        Category business = new Category("BUSINESS");
        Category economy = new Category("ECONOMY");

        categoryService.save(business);
        categoryService.save(economy);
        System.out.println("Категории \"Busines\" и \"Economy\" созданы и сохранены в БД!");
    }
}

package app.config;

import lombok.extern.log4j.Log4j2;
<<<<<<< src/main/java/app/config/DataInitializer.java
import app.entities.CountryCode;
import app.entities.Destination;
import app.services.DestinationService;
=======
import app.entities.Category;
import app.services.CategoryService;
import app.services.PassengerService;
import app.util.PassengerAndPassportCreator;
>>>>>>> src/main/java/app/config/DataInitializer.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */
@Log4j2
@Component
public class DataInitializer {

<<<<<<< src/main/java/app/config/DataInitializer.java

=======
    private final PassengerService passengerService;
    public final CategoryService categoryService;
    private final DestinationService ds;

    @Autowired
    public DataInitializer(PassengerService passengerService, CategoryService categoryService, DestinationService ds) {
        this.passengerService = passengerService;
        this.categoryService = categoryService;
        this.ds = ds;
    }


>>>>>>> src/main/java/app/config/DataInitializer.java
    @PostConstruct
    public void init() {
        Destination destination = new Destination();
        destination.setSity("Moscow");
        destination.setCountryCode(CountryCode.RUS_643);
        destination.setCountry_name("Russia");
        destination.setAirport_name("Domodedovo");
        destination.setAirport_code("DME");
        destination.setTimezone(TimeZone.getTimeZone("Europe/Moscow"));
        ds.save(destination);
        System.out.println("DataInitializer сработал!");
        log.info("Тест логгера");

        createCategory();
    }

    @PostConstruct
    public void addFivePassengersToDB() {
        PassengerAndPassportCreator.createFivePassengerAndSaveInDB(passengerService);
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

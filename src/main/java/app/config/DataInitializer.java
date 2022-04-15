package app.config;

import app.entities.Admin;
import app.entities.AirlineManager;
import app.entities.User;
import app.services.UserService;
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
@Component
public class DataInitializer {

    private final PassengerService passengerService;
    public final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public DataInitializer(PassengerService passengerService, CategoryService categoryService, UserService userService) {
        this.passengerService = passengerService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");

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

    @PostConstruct
    public void initUser() {
        User userAdmin = new Admin();
        userAdmin.setFirstName("Viktor");
        userAdmin.setLastName("Lipin");
        userAdmin.setAge(35);
        userAdmin.setEmail("tyz_ft@list.ru");
        userAdmin.setPassword("lipin");
        User userairlineManager = new AirlineManager();
        userairlineManager.setFirstName("Anna");
        userairlineManager.setLastName("Ivanova");
        userairlineManager.setAge(25);
        userairlineManager.setEmail("ann@mail.ru");
        userairlineManager.setPassword("ivanova");
        userService.addUser(userairlineManager);
        userService.addUser(userAdmin);
        System.out.println("initUser сработал!");
    }
}

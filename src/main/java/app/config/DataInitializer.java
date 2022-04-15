package app.config;


import app.entities.*;
import app.services.ApplicationUserRoleService;
import app.services.UserService;
import app.util.ApplicationUserRolesCreator;
import lombok.extern.log4j.Log4j2;
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
    private final UserService userService;
    private final ApplicationUserRoleService applicationUserRoleService;

    @Autowired
    public DataInitializer(PassengerService passengerService,
                           CategoryService categoryService,
                           UserService userService,
                           ApplicationUserRoleService applicationUserRoleService) {
        this.passengerService = passengerService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.applicationUserRoleService = applicationUserRoleService;
    }

    @PostConstruct
    public void init() {
        initUsers();
        addFivePassengersToDB();
        createCategory();
        addRolesToDB();
        System.out.println("DataInitializer сработал!");
    }



    private void addFivePassengersToDB() {
        PassengerAndPassportCreator.createFivePassengerAndSaveInDB(passengerService);
        log.info("Тест логгера");
    }

    private void addRolesToDB() {
        ApplicationUserRolesCreator.createAllRolesAndSaveInDB(applicationUserRoleService);
        log.info("Роли добавились в лог");
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

    private void initUsers() {
        AbstractUser admin = new Admin();
        admin.setFirstName("Viktor");
        admin.setLastName("Lipin");
        admin.setAge(35);
        admin.setEmail("tyz_ft@list.ru");
        admin.setPassword("lipin");

        AbstractUser airlineManager = new AirlineManager();
        airlineManager.setFirstName("Anna");
        airlineManager.setLastName("Ivanova");
        airlineManager.setAge(25);
        airlineManager.setEmail("ann@mail.ru");
        airlineManager.setPassword("ivanova");

        AbstractUser user = new User();
        user.setFirstName("Oleg");
        user.setLastName("Olegov");
        user.setAge(23);
        user.setEmail("asd_ft@list.ru");
        user.setPassword("user");

        userService.addUser(airlineManager);
        userService.addUser(admin);
        userService.addUser(user);

        System.out.println("initUser сработал!");
    }
}

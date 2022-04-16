package app.config;


import app.entities.*;
import app.services.ApplicationUserRoleService;
import app.services.ApplicationUserService;
import app.util.ApplicationUserRolesUtil;
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
    private final ApplicationUserService applicationUserService;
    private final ApplicationUserRoleService applicationUserRoleService;

    @Autowired
    public DataInitializer(PassengerService passengerService,
                           CategoryService categoryService,
                           ApplicationUserService applicationUserService,
                           ApplicationUserRoleService applicationUserRoleService) {
        this.passengerService = passengerService;
        this.categoryService = categoryService;
        this.applicationUserService = applicationUserService;
        this.applicationUserRoleService = applicationUserRoleService;
    }

    @PostConstruct
    public void init() {
        addRolesToDB();
        initUsers();
        addFivePassengersToDB();
        createCategory();
        System.out.println("DataInitializer сработал!");
    }



    private void addFivePassengersToDB() {
        PassengerAndPassportCreator.createFivePassengerAndSaveInDB(passengerService);
        log.info("Тест логгера");
    }

    private void addRolesToDB() {
        ApplicationUserRolesUtil.createAllRolesAndSaveInDB(applicationUserRoleService);
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
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setAge(35);
        admin.setEmail("admin@mail.ru");
        admin.setPassword("admin");

        AbstractUser airlineManager = new AirlineManager();
        airlineManager.setFirstName("Manager");
        airlineManager.setLastName("Managerov");
        airlineManager.setAge(25);
        airlineManager.setEmail("airlinemanager@mail.ru");
        airlineManager.setPassword("airlineManager");

        AbstractUser user = new User();
        user.setFirstName("User");
        user.setLastName("Userov");
        user.setAge(23);
        user.setEmail("user@mail.ru");
        user.setPassword("user");


        applicationUserService.addUser(admin);
        applicationUserService.addUser(airlineManager);
        applicationUserService.addUser(user);

        System.out.println("Пользователи приложения добавлены в БД");
    }
}

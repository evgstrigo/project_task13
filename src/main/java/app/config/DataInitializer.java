package app.config;

import app.entities.Admin;
import app.entities.AirlineManager;
import app.entities.User;
import app.services.UserService;
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

    private final UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");
    }

    @PostConstruct
    public void initUser() {
        User userAdmin = new Admin(10);
        userAdmin.setFirstName("Viktor");
        userAdmin.setLastName("Lipin");
        userAdmin.setAge(35);
        userAdmin.setEmail("tyz_ft@list.ru");
        userAdmin.setPassword("lipin");
        User userAirlineManager = new AirlineManager(true);
        userAirlineManager.setFirstName("Anna");
        userAirlineManager.setLastName("Ivanova");
        userAirlineManager.setAge(25);
        userAirlineManager.setEmail("ann@mail.ru");
        userAirlineManager.setPassword("ivanova");
        userService.addUser(userAirlineManager);
        userService.addUser(userAdmin);
        System.out.println("initUser сработал!");
    }
}

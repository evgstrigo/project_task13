package app.config;

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

    @Autowired
    public DataInitializer(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");


    }

    @PostConstruct
    public void addFivePassengersToDB() {
        PassengerAndPassportCreator.createFivePassengerAndSaveInDB(passengerService);
    }
}

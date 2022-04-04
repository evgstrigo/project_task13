package app.config;

import app.entities.CountryCode;
import app.entities.Destination;
import app.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * В этом классе инициализируются тестовые данные для базы.
 * Эти данные будут каждый раз создаваться заново при поднятии SessionFactory и удаляться из БД при её остановке.
 * Инжектьте и используйте здесь соответствующие сервисы ваших сущностей."
 */
@Component
public class DataInitializer {

    private final DestinationService ds;

    @Autowired
    public DataInitializer(DestinationService ds) {
        this.ds = ds;
    }

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
    }
}

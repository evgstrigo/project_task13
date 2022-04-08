package app.config;

import lombok.extern.log4j.Log4j2;
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

    @PostConstruct
    public void init() {
        System.out.println("DataInitializer сработал!");
        log.info("Тест логгера");
    }
}

package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок от AbstractApplicationUser<br>
 * При сохранении в БД экземпляра этого класса в сервисе ему будет присвоена роль AIRLINE_MANAGER<br>
 * JsonDeserialize используется для настройки десериализации Json.
 * DiscriminatorValue в таблице базы данных User будет столбец airline_manager указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "airline_manager")
@NoArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = AirlineManager.class)
public class AirlineManager extends AbstractApplicationUser {

}

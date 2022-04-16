package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок от AbstractApplicationUser<br>
 * При сохранении в БД экземпляра этого класса в сервисе ему будет присвоена роль USER<br>
 * JsonDeserialize используется для настройки десериализации Json.
 * DiscriminatorValue в таблице базы данных User будет столбец admin указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "user")
@NoArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = User.class)
public class User extends AbstractApplicationUser {

}
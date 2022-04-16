package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок от AbstractApplicationUser<br>
 * При сохранении в БД экземпляра этого класса в сервисе ему будет присвоена роль ADMIN<br>
 *  JsonDeserialize используется для настройки десериализации Json.<br>
 *  DiscriminatorValue в таблице базы данных AbstractApplicationUser будет столбец admin указывающий<br>
 * на конкретный класс потомок.
 */

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "admin")
@Setter
@Getter
@JsonDeserialize(as = Admin.class)
public class Admin extends AbstractApplicationUser {

}

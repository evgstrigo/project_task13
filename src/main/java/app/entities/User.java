package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок, также используем lombok для простоты настройки.
 * @JsonDeserialize используется для настройки десериализации Json.
 * @DiscriminatorValue в таблице базы данных User будет столбец admin указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "user")
@NoArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = User.class)
public class User extends AbstractUser {

}
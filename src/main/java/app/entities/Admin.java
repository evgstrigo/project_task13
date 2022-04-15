package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок, также используем lombok для простоты настройки.
 * @JsonDeserialize используется для настройки десериализации Json.
 * @DiscriminatorValue в таблице базы данных User будет столбец admin указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = Admin.class)
public class Admin extends User {
}

package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок, также используем lombok для простоты настройки(пока не используется).
 * @JsonDeserialize используется для настройки десериализации Json.
 * @DiscriminatorValue в таблице базы данных User будет столбец passenger указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "passenger")
@JsonDeserialize(as = Passenger.class)
public class Passenger extends User {
}

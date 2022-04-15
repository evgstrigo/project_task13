package app.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Класс потомок, также используем lombok для простоты настройки.
 * @JsonDeserialize используется для настройки десериализации Json.
 * @DiscriminatorValue в таблице базы данных User будет столбец airline_manager указывающий
 * на конкретный класс потомок.
 */

@Entity
@DiscriminatorValue(value = "airline_manager")
@AllArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = AirlineManager.class)
public class AirlineManager extends AbstractUser {

    @Override
    public void initMethod(@Qualifier(value = "airlineManagerRole") ApplicationUserRole applicationUserRole) {
        this.setRole(applicationUserRole);
    }

}
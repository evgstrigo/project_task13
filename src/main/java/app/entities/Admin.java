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
@DiscriminatorValue(value = "admin")
@AllArgsConstructor
@Setter
@Getter
@JsonDeserialize(as = Admin.class)
public class Admin extends AbstractUser {

//    @Override
//    public void initMethod(@Qualifier(value = "adminRole") ApplicationUserRole applicationUserRole) {
//        this.setRole(applicationUserRole);
//        System.out.println("Сработал initMethodAdmin" + applicationUserRole.getValue());
//    }
}

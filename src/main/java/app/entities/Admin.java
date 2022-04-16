package app.entities;

import app.services.ApplicationUserRoleService;
import app.util.ApplicationUserRolesUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Класс потомок, также используем lombok для простоты настройки.
 *
 * @JsonDeserialize используется для настройки десериализации Json.
 * @DiscriminatorValue в таблице базы данных User будет столбец admin указывающий
 * на конкретный класс потомок.
 */

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "admin")
@Setter
@Getter
@JsonDeserialize(as = Admin.class)
public class Admin extends AbstractUser {

}

package app.entities;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Сущность Role для Spring Security. Сделана без использования Enum.<br>
 * Значение роли в виде строки будет храниться в поле value
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicationUserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Хранит в себе значение роли в виде строки.
     */
    private String value;

    public ApplicationUserRole(String value) {
        this.value = value;
    }

    /**
     * Используется в Spring Security<br>
     * @return Возвращает строковое название роли
     */
    @Override
    public String getAuthority() {
        return this.value;
    }
}

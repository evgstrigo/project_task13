package app.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Абстрактный класс который описывает основные поля для классов потомков пользователей
 * таких как Admin, AirlineManager, User. Используется для Spring Security.<br>
 * Для сохранения пользователей в базе данных используется SINGLE_TABLE, т.е. все зависимые
 * классы будут храниться в одной таблице User.
 * Т.к. Json не знает как десериализовать абстрактные класс, используются @JsonTypeInfo.
 * Для простоты настройки класса используем lombok.
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "admin"),
        @JsonSubTypes.Type(value = AirlineManager.class, name = "airline_manager"),
        @JsonSubTypes.Type(value = User.class, name = "user")
        }
)
@ApiModel("This is abstract class ApplicationUser")
public abstract class AbstractApplicationUser implements UserDetails {
    /**
     * Идентификатор абстрактного класса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1", notes = "This is id")
    private Long id;
    /**
     * Имя пользователя
     */
    @ApiModelProperty(example = "Ivan", notes = "This is id")
    private String firstName;
    /**
     * Фамилия пользователя
     */
    @ApiModelProperty(example = "Ivanov", notes = "This is lastName")
    private String LastName;
    /**
     * Возраст пользователя
     */
    @ApiModelProperty(example = "50", notes = "This is Age")
    private int age;
    /**
     * Электронный адресс<br>
     * Not null
     */
    @ApiModelProperty(example = "ivan@mail.ru", notes = "This is Email")
    @Column(unique = true)
    @NotNull
    private String email;
    /**
     * Пароль пользователя
     * Not null
     */
    @ApiModelProperty(example = "password", notes = "This is password")
    @NotNull
    private String password;


    /**
     * Роль пользователя. На данный момент у одного пользователя одна роль.<br>
     * Но можно сделать и Set при необходимости
     */
    @ApiModelProperty(example = "ADMIN", notes = "This is role")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private ApplicationUserRole role;


    /**
     * Используется для Spring Security<br>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + this.role.getAuthority()));
        return grantedAuthorityList;
    }

    /**
     * Используется для Spring Security<br>
     * В качестве username используется email
     * @return
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * Ниже представлены несколько методов для Spring Security<br>
     * На данный момент они не задействованы, поэтому все возвращают true<br>
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

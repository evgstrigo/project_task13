package app.entities;

import ch.qos.logback.core.boolex.EvaluationException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * Абстрактный класс который описывает основные поля для классов потомков пользователей
 * таких как Admin, Passenger, AirlineManager.
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.EXTERNAL_PROPERTY,  property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Admin.class, name = "admin"),
        @JsonSubTypes.Type(value = AirlineManager.class, name = "airline_manager"),
        @JsonSubTypes.Type(value = Passenger.class, name = "passenger") }
)
@ApiModel("This is abstract class User")
public abstract class User {
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
     * Электронный адресс
     */
    @ApiModelProperty(example = "ivan@mail.ru", notes = "This is Email")
    private String email;
    /**
     * Пароль пользователя
     */
    @ApiModelProperty(example = "password", notes = "This is password")
    private String password;
}

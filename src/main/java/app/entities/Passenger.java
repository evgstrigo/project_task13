package app.entities;

import app.repositories.PassengerRepository;
import app.services.PassengerServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * Passenger entity
 */

@Entity
@Data
@NoArgsConstructor
@ApiModel(description = "This is passenger model")
public class Passenger {


    /**
     * Id of passenger. Generates automatically by DB
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ApiModelProperty(example = "0", notes = "This is id")
    private long id;


    /**
     * Has format email@email.ru <br>
     * Not null
     */
    @Column(name = "email", unique = true)
    @NotNull
    @Email
    @ApiModelProperty(example = "evgstrigo@mail.ru", notes = "This is email", required = true)
    private String email;


    /**
     * Bidirectional to app.entities.{@link Passport} <br>
     * a @NotNull is deactivated for tests (because it's necessary to add correct passport id into dataset)
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passport_id")
//    @NotNull
    @ApiModelProperty(example = "passport", notes = "This is passport", required = true)
    @JsonManagedReference
    private Passport passport;

}


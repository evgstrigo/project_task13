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
    @ApiModelProperty(example = "1", notes = "This is id")
    private long id;


    /**
     * Email of passenger. Has format email@email.ru
     */
    @Column(name = "email", unique = true)
    @NotNull
    @Email
    @ApiModelProperty(example = "evgstrigo@mail.ru", notes = "This is email", required = true)
    private String email;



    /**
     * Passport of passenger.<p> Is another entity <p>  @see app.entities.{@link app.entities.Passport}
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "passport_id")
    @NotNull
    @ApiModelProperty(example = "passport", notes = "This is passport", required = true)
    @JsonManagedReference
    private Passport passport;

}


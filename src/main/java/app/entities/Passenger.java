package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * Passenger entity
 */

@Entity
@Table(name = "passenger")
@Data
@NoArgsConstructor
@ApiModel(description = "This is passenger model")
public class Passenger {


    /**
     * Id of passenger. Generates automatically by DB
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @ApiModelProperty(example = "1", notes = "This is id")
    private long id;


    /**
     * Email of passenger. Has format email@email.ru
     */
    @Column(name = "email", unique = true)
    @NonNull
    @Email
    @ApiModelProperty(example = "evgstrigo@mail.ru", notes = "This is email", required = true)
    private String email;


    /**
     * First name of passenger.
     */
    @Column(name = "first_name")
    @NonNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Eugenio", notes = "This is first name", required = true)
    private String firstName;


    /**
     * Last name of passenger.
     */
    @Column(name = "last_name")
    @NonNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Strigo", notes = "This is last name", required = true)
    private String lastName;


    /**
     * Middle name of passenger, if present.<p> This field is not required
     */
    @Column(name = "middle_name")
    @ApiModelProperty(example = "Viktorovich", notes = "This is middle name")
    private String middleName;


    /**
     * Date of birth of passenger.<p>
     * Has format dd-MM-yyyy.
     */
    @Column(name = "date_of_birth")
    @NonNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @ApiModelProperty(example = "31-05-1989", notes = "This is date of birth", required = true)
    private LocalDate dateOfBirth;


    /**
     * Passport of passenger.<p> Is another entity <p>  @see app.entities.{@link app.entities.Passport}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    @NonNull
    @ApiModelProperty(example = "passport", notes = "This is passport", required = true)
    private Passport passport;

}

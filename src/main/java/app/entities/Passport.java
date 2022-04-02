package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


/**
 * Passport entity
 */

@Entity
@Table(name = "passport")
@Data
@NoArgsConstructor
@ApiModel(description = "This is passenger's passport model")
public class Passport {


    /**
     * Id of passenger. Generates automatically by DB <p>
     * In task #1 it's not specified,
     * but I think this entity should have similar primary key (not on field seriesAndNumber)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @ApiModelProperty(example = "1", notes = "This is id")
    private Long id;


    /**
     * First name specified in passport
     */
    @Column(name = "first_name")
    @Nullable
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Eugenio", notes = "This is first name", required = true)
    private String firstName;


    /**
     * Last name specified in passport
     */
    @Column(name = "last_name")
    @NonNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Strigo", notes = "This is last name", required = true)
    private String lastName;


    /**
     * Middle name in passport, if present.<p> This field is not required
     */
    @Column(name = "middle_name")
    @ApiModelProperty(example = "Viktorovich", notes = "This is middle name", required = false)
    private String middleName;


    /**
     * Gender in passport.<p> Could have values "m"/ "M" / "f" / "F" / "м"/ "М" / "ж" / "Ж" in EN & RU
     */
    @Column(name = "gender")
    @NonNull
    @Pattern(regexp = "[mMfFмМжЖ]")
    @ApiModelProperty(example = "M", notes = "This is gender", required = true)
    private String gender;


    /**
     * Birthplace specified in passport
     */
    @Column(name = "birthplace")
    @NonNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Minsk", notes = "This is birthplace", required = true)
    private String birthplace;


    /**
     * Residence registration specified in passport
     */
    @Column(name = "residence_registration")
    @NonNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Moscow, Kremlin", notes = "This is residence registration", required = true)
    private String residenceRegistration;


    /**
     * Date of birth specified in passport<p>
     * Has format dd-MM-yyyy.
     */
    @Column(name = "date_of_birth")
    @NonNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @ApiModelProperty(example = "31-05-2020", notes = "This is date of birth", required = true)
    private LocalDate dateOfBirth;


    /**
     * Serial number of passport
     */
    @Column(name = "series_and_number", unique = true)
    @NonNull
    @ApiModelProperty(example = "3526202", notes = "This is serial number", required = true)
    private int seriesAndNumber;

}

package app.entities;

import app.util.LocalDateUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


/**
 * Passport entity
 */

@Entity
@Data
@NoArgsConstructor
@ApiModel(description = "This is passenger's passport model")
public class Passport {


    /**
     * Id of passenger. Generates automatically by DB <br>
     * In task #1 it's not specified, <br>
     * but I think this entity should have similar primary key (not on field seriesAndNumber)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ApiModelProperty(example = "1", notes = "This is id")
    private Long id;


    /**
     * First name specified in passport<br>
     * Min length 2, max 25<br>
     * Not null
     */
    @NotNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Eugenio", notes = "This is first name", required = true)
    private String firstName;


    /**
     * Last name specified in passport<br>
     * Min length 2, max 25<br>
     * Not null
     */
    @NotNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Strigo", notes = "This is last name", required = true)
    private String lastName;


    /**
     * Middle name in passport, if present.<br>
     * Nullable
     */
    @ApiModelProperty(example = "Viktorovich", notes = "This is middle name", required = false)
    private String middleName;


    /**
     * Gender in passport.<br>
     * Could have values "m"/ "M" / "f" / "F"  in EN<br>
     * Not null
     */
    @NotNull
    @Pattern(regexp = "[mMfFмМжЖ]")
    @ApiModelProperty(example = "M", notes = "This is gender", required = true)
    private String gender;


    /**
     * Birthplace specified in passport<br>
     * Min length 2, max 25<br>
     * Not null
     */
    @NotNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Minsk", notes = "This is birthplace", required = true)
    private String birthplace;
//
//
    /**
     * Residence registration specified in passport<br>
     * Min length 2, max 25<br>
     * Not null
     */
    @NotNull
    @Length(min = 2, max = 25)
    @ApiModelProperty(example = "Moscow, Kremlin", notes = "This is residence registration", required = true)
    private String residenceRegistration;
//
//
    /**
     * Date of birth specified in passport<br>
     * Has format dd-MM-yyyy. (i.e. 31-05-2020)<br>
     * Not null
     */
    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @ApiModelProperty(example = "31-05-2020", notes = "This is date of birth", required = true)
    private LocalDate dateOfBirth;

    public void setDateOfBirth(String dateOfBirth) {
        System.out.println("Setters starts to work");
       this.dateOfBirth = LocalDateUtil.createLocalDateFromString(dateOfBirth);
    }
//
//
    /**
     * Serial number of passport<br>
     * Has unique constraint<br>
     * Not null
     */
    @Column(name = "series_and_number", unique = true)
    @NotNull
    @ApiModelProperty(example = "12 34 123456", notes = "This is serial number", required = true)
    private String seriesAndNumber;

//
    @OneToOne(mappedBy = "passport",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonBackReference
    private Passenger passenger;

}

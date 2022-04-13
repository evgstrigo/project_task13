package app.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.TimeZone;

/**
 * эта сущность описывает пункт прилёта/вылета
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ApiModel(description = "This is Destination class")
public class Destination {
    /**
     * идентификатор id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1", notes = "This is id")
    private Long id;
    /**
     * наименование города
     */
    @NonNull
    @ApiModelProperty(example = "Vladivostok", notes = "This is sity")
    private String sity;
    /**
     * код страны
     */
    @Enumerated(EnumType.STRING)
    @NonNull
    @ApiModelProperty(example = "RUS_643", notes = "This is countryCode")
    private CountryCode countryCode;
    /**
     * наименование страны
     */
    @NonNull
    @ApiModelProperty(example = "Russia", notes = "This is country_name")
    private String country_name;
    /**
     * наименование аэропорта
     */
    @NonNull
    @ApiModelProperty(example = "Domodedovo", notes = "This is airport_name")
    private String airport_name;
    /**
     * код аэропорта
     */
    @NonNull
    @ApiModelProperty(example = "DME", notes = "This is airport_code")
    private String airport_code;
    /**
     * временная зона
     */
    @NonNull
    @ApiModelProperty(example = "Europe/Moscow", notes = "This is timezone")
    private TimeZone timezone;
}

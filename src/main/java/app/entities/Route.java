package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Route entity.<br>
 * Route is a set of data about specific flight.<br>
 * Route represents as a line in search result.
 */

@Entity
@Table(name = "Route")
@Data
@NoArgsConstructor
public class Route {


    /**
     * Id of route. Generates automatically by DB
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    @Setter(AccessLevel.NONE)
    private Long id;


    /**
     * Destination "FROM" of route.<br>
     * No deleting on cascade
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "destination_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NonNull
    private Destination from;


    /**
     * Destination "TO" of route.<br>
     * No deleting on cascade
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "destination_id", referencedColumnName = "id", insertable = false, updatable = false)
    @NonNull
    private Destination to;


    /**
     * Date of departure.<p>
     * Has format dd-MM-yyyy.
     */
    @Column(name = "departure_date")
    @Future
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate departureDate;


    /**
     * Date of arrival.<p>
     * Has format dd-MM-yyyy.
     */
    @Column(name = "arrival_date")
    @Future
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate arrivalDate;


    /**
     * Number of seats in current route.
     */
    @Column(name = "number_of_seats")
    @NonNull
    @Min(value = 1)
    @Max(value = 500)
    private Integer numberOfSeats;


    /**
     * Category of current route.<br>
     * No deleting on cascade. <br>
     * One-to-One unidirectional.
     */
    @OneToOne(cascade =  {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NonNull
    private Category category;

}

/**
 *  This is FAKE entity for main Route entity
 */
@Entity
class Category {
    @Id
    private Long id;
}

/**
 *  This is FAKE entity for main Route entity
 */
@Entity
class Destination {
    @Id
    private Long id;
}
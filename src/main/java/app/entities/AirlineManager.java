package app.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "air_lane_manager")
public class AirlineManager extends User {

    private boolean higherEducation;
}

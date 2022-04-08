package app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "air_lane_manager")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AirlineManager extends User {

    private boolean higherEducation;
}

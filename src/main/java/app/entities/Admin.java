package app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Admin extends User {

    private int workExperienceInAge;
}

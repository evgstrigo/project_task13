package app.entities;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicationUserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public ApplicationUserRole(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return this.value;
    }
}

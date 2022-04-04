package app.entities;

import lombok.*;
import javax.persistence.*;

/**
*Category entity
 */

@Entity
@Table(name = "category")
@Data
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name_category")
    @NonNull
    private String nameCategory;

    public Category() {
    }
}

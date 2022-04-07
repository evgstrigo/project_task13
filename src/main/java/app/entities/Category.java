package app.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Класс сущности "Category". Нужен для возможности выбора места в разных зонах воздушного судна. Имеет поле ID и nameCategory.
 */

@Entity
@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "This is category model")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1", notes = "This is id")
    private Long id;

    @NonNull
    @ApiModelProperty(example = "BUSINESS", notes = "This is name Categories", required = true)
    private String name;

}

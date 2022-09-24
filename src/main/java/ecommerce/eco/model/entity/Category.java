package ecommerce.eco.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "description cannot be empty")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Product> products;
    private final boolean softDeleted = Boolean.FALSE;
}

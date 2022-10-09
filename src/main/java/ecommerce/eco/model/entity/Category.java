package ecommerce.eco.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



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
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
    private boolean softDeleted;
}

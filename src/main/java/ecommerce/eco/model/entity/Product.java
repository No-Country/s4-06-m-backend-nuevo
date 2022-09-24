package ecommerce.eco.model.entity;

import ecommerce.eco.util.ColorEnum;
import ecommerce.eco.util.SexEnum;
import ecommerce.eco.util.SizeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int stock;
    private int score;
    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;
    private String brand;
    private SizeEnum size;
    private ColorEnum color;
    private SexEnum sex;
    private boolean offer; // producto en oferta
    private boolean softDeleted = Boolean.FALSE;
    @ManyToMany
    @JoinTable(
            name = "product_whit_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "category", updatable = true, nullable = true)
    private List<Category> categories;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", stock=").append(stock);
        sb.append(", score=").append(score);
        sb.append(", price=").append(price);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", size=").append(size);
        sb.append(", color=").append(color);
        sb.append(", sex=").append(sex);
        sb.append(", offer=").append(offer);
        sb.append(", softDeleted=").append(softDeleted);
        sb.append('}');
        return sb.toString();
    }
}

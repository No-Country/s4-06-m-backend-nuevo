package ecommerce.eco.model.entity;

import ecommerce.eco.util.ColorEnum;
import ecommerce.eco.util.SexEnum;
import ecommerce.eco.util.SizeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    @NotNull(message = "description cannot be empty")
    private String description;

    private int stock;
    private int score;
    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;
    private String brand; // Marca
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
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;


}

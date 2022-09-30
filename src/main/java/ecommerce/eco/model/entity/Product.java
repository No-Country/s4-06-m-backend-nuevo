package ecommerce.eco.model.entity;

import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SexEnum;
import ecommerce.eco.model.enums.SizeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "description cannot be empty")
    private String shortDetails;
    private String details;
    private String title;
    @NotEmpty(message = "Brand cannot be empty")
    private String brand; // Marca
    private String view;
    private boolean stock;

    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;

    @OneToOne()
    @JoinColumn(name = "id_size")
    private Size size;
    @OneToOne()
    @JoinColumn(name = "id_color")
    private Color color;

    private boolean softDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Image> carrousel = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
    // ********************************************* //

}

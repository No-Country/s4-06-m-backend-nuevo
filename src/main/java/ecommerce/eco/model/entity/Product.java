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
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NotEmpty(message = "description cannot be empty")
    private String shortDetails;
    private String details;
    private String title;
    @NotEmpty(message = "Brand cannot be empty")
    private String brand; // Marca
    private String view;
    private boolean stock;
    private double stars = 0.0;
    private int discount; // descuento
    private int contadorEstrellas = 0;
    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Size> sizes;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Color> colors;

    private boolean softDeleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Image> carrousel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    // ********************************************* //
    public void addReviews(Review review) {
        reviews.add(review);
        contadorEstrellas++;
    }


    public void agregarEstrella(double score) {

        stars = ((stars * contadorEstrellas) + score) / contadorEstrellas + 1;
        //resetearContador();
        //stars = Math.round(stars);
    }

    public void resetearContador() {
        contadorEstrellas = 0;
    }

    public double discount() {
        return (price * discount) / 100;
    }

    public void addBColor(Color color) {
        colors.add(color);
    }


}

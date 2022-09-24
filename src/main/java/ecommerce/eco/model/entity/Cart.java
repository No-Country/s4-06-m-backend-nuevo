package ecommerce.eco.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import ecommerce.eco.util.CartEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CartEnum state;// estado

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    private boolean softDeleted = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "registration", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registration;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

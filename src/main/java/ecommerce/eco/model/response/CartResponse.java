package ecommerce.eco.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.enums.CartEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartResponse {
    private Long id;

    private CartEnum state;// estado

    private List<ProductCartResponse> products = new ArrayList<>();

    private boolean softDeleted = Boolean.FALSE;

    private LocalDateTime registration;


    private UserResponse user;
}

package ecommerce.eco.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import ecommerce.eco.model.enums.CartEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CartRequest {
    private CartEnum state;// estado
    @CreationTimestamp
    @Column(name = "registration", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registration;
}

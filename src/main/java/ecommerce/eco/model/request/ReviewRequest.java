package ecommerce.eco.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewRequest {
    @NotEmpty(message = "idProduct no puede estar vacio")
    private Long idProduct;
    private int score; // estrellas o puntaje
    //private String username; // nombre del usuario
    private String comment; // comentario
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //private ZonedDateTime time;
}

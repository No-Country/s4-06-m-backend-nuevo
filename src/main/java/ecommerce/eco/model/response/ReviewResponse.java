package ecommerce.eco.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponse {

    private int score; // estrellas o puntaje
    private String username; // nombre del usuario
    private String comment; // comentario
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime time;
}

package ecommerce.eco.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double score; // estrellas o puntaje
    private String username; // nombre del usuario
    private String comment; // comentario
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime time;
    private boolean softDeleted = Boolean.FALSE;

}

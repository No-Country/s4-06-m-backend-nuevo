package ecommerce.eco.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    @NonNull
    @NotEmpty(message = "the full name can't be null")
    @Email(message = "Email should have a valid format")
    @NotBlank(message = "Email cannot be empty.")
    private String email;
    @NonNull
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;
}
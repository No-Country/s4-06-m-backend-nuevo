package ecommerce.eco.model.request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    @NonNull
    @NotEmpty(message = "the full name can't be null")
    @NotBlank(message = "First Name Required")
    private String fullName ;
    @NonNull
    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;
}

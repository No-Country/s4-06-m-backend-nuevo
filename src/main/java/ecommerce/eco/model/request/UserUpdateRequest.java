package ecommerce.eco.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserUpdateRequest {
    @NotBlank(message = "FullName name Required")
    private String fullName;
    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;
}

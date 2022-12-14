package ecommerce.eco.model.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@Builder
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotBlank(message = "FullName name Required")
    @NotEmpty(message = "the full name can't be null")
    private String fullName;
    @NotBlank(message = "Email cannot be empty.")
    @NotEmpty(message = "the email can't be null")
    @NonNull
    @Email
    private String email;
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;
    private boolean softDeleted = Boolean.FALSE;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @JoinColumn(name="image_id")
    @OneToOne(cascade = CascadeType.REFRESH)
    private Image image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.softDeleted;
    }


}




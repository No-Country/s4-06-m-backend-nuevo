package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.response.AuthResponse;
import ecommerce.eco.service.abstraction.RoleService;
import ecommerce.eco.model.enums.RolesEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ImageMapper imageMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    public User entityToDto(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findBy(RolesEnum.USER.getFullRoleName()))
                .build();
    }
    public AuthResponse dtoToEntity(User user) {
      return AuthResponse.builder()
              .id(user.getId())
              .email(user.getEmail())
              .role(user.getRole().getName())
              .fullName(user.getFullName())
              .build();
    }
}

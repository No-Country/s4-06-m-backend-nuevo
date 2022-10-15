package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Cart;
import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.request.UserUpdateRequest;
import ecommerce.eco.model.response.AuthResponse;
import ecommerce.eco.model.response.UserResponse;
import ecommerce.eco.service.abstraction.CartService;
import ecommerce.eco.service.abstraction.ImageService;
import ecommerce.eco.service.abstraction.RoleService;
import ecommerce.eco.model.enums.RolesEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ImageMapper imageMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ImageService imageService;

    private final CartService cartService;

    public User entityToDto(UserRequest request) throws IOException {
        return User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findBy(RolesEnum.USER.getFullRoleName()))
                .image(imageService.imageUser(imageService.userDefault()))
                .softDeleted(Boolean.FALSE)
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
    public UserResponse dtoToEntityUser(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .image(imageMapper.imageToDto(user.getImage()))
                .build();
    }
    public  User updateToMaper(User user, UserUpdateRequest request, Image img){
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setImage(img);
        return  user;
    }
}

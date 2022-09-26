package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.request.UserUpdateRequest;
import ecommerce.eco.model.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User getInfoUser() ;
    UserResponse getById(Long id);
    void delete(Long id);
    List<UserResponse> getAll();
    UserResponse update(UserUpdateRequest request, MultipartFile image);
}

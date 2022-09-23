package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.request.AuthRequest;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.response.AuthResponse;

public interface AuthService {
    AuthResponse register(UserRequest request);

    AuthResponse login(AuthRequest request);
}

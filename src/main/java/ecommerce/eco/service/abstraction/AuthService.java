package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.request.AuthRequest;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.response.AuthResponse;

import java.io.IOException;

public interface AuthService {
    AuthResponse register(UserRequest request) throws IOException;

    AuthResponse login(AuthRequest request);
}

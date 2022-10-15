package ecommerce.eco.service;

import ecommerce.eco.config.utils.JwtUtil;
import ecommerce.eco.exception.InvalidCredentialsException;
import ecommerce.eco.exception.UserAlreadyExistException;
import ecommerce.eco.model.entity.Cart;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.enums.CartEnum;
import ecommerce.eco.model.mapper.CartMapper;
import ecommerce.eco.model.mapper.UserMapper;
import ecommerce.eco.model.request.AuthRequest;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.response.AuthResponse;
import ecommerce.eco.repository.UserRepository;
import ecommerce.eco.service.abstraction.AuthService;
import ecommerce.eco.service.abstraction.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtUtil jwtToken;
    private final CartService cartService;

    private final CartMapper cartMapper;



    private final AuthenticationManager authenticationManager;

    private User getUser(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null || !user.isEnabled()) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }
        return user;
    }
    @Override
    public AuthResponse register(UserRequest request) throws IOException {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new UserAlreadyExistException("Email is already in use.");
        }
        User userCreate = userRepository.save(userMapper.entityToDto(request));
        Cart cart = new Cart();
        cart.setRegistration(LocalDateTime.now());
        cart.setState(CartEnum.OPEN);
        userCreate.setCart(cart);
        cartService.save(cart);
        userCreate.setCart(cart);
        AuthResponse response = userMapper.dtoToEntity(userCreate);
        response.setToken(jwtToken.generateToken(userCreate));
        return response;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticate(request);
        User user = getUser(request.getEmail());
        LOGGER.warn("Correo:" + user.getEmail());
        AuthResponse a=userMapper.dtoToEntity(user);
        a.setToken(jwtToken.generateToken(user));
        return a;
    }

    private void authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        }catch (Exception e){
            throw new InvalidCredentialsException("Invalid email or password.");
        }
    }
}

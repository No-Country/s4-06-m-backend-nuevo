package ecommerce.eco.controller;

import ecommerce.eco.model.request.AuthRequest;
import ecommerce.eco.model.request.UserRequest;
import ecommerce.eco.model.response.AuthResponse;
import ecommerce.eco.service.abstraction.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(value = "Authenticate Controller", description = "Authentication handler login/register")
public class AuthController {
    private final AuthService authService;
    /*@ApiOperation(value = "Registration method", notes = "Returns a registered user" )*/
    @ApiOperation(value = "User register the system", notes = "Returns a register user" )
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRequest request) throws IOException {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "User login to the system", notes = "Returns a login user" )
    @PostMapping("/login")
    private ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/logout")
    @ApiOperation(value = "User logout to the system", notes = "Returns a logout user" )
    public ResponseEntity<?> logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("Cerrado correcto");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

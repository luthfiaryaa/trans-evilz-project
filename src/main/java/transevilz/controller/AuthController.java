package transevilz.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dto.*;
import transevilz.jwt.JwtUtils;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")

public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        return authService.authenticateUser(loginRequest, servletResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/signin/admin")
    public ResponseEntity<?> authenticationAdmin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        return authService.authenticateAdmin(loginRequest, servletResponse);
    }
}


package transevilz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.repository.UserRepository;
import transevilz.domain.dto.LoginRequest;
import transevilz.domain.dto.SignupRequest;
import transevilz.services.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return authService.authenticateUser(loginRequest, servletRequest, servletResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }


}


package transevilz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dao.User;
import transevilz.domain.dto.LoginRequest;
import transevilz.jwt.JwtUtils;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/backoffice")
public class BackOfficeController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticationAdmin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        return authService.authenticateAdmin(loginRequest, servletResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser() {
        return authService.getUser();
    }

    @GetMapping("/user/{id}")
    public User getUserId(@PathVariable("id") Long id){
        return authService.getUserId(id);
    }

}

package transevilz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dao.User;
import transevilz.domain.dto.LoginRequest;
import transevilz.domain.dto.SignupRequest;
import transevilz.jwt.JwtUtils;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;
import transevilz.services.BackOfficeService;
import transevilz.services.ForgetPasswordService;
import transevilz.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/backoffice")
public class BackOfficeController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private BackOfficeService backOfficeService;

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return backOfficeService.addUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticationAdmin(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        return authService.authenticateAdmin(loginRequest, servletResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUser() {
        return backOfficeService.getUser();
    }

    @GetMapping("/users/{id}")
    public User getUserId(@PathVariable("id") Long id){
        return backOfficeService.getUserId(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUsers(@PathVariable("id") Long id, @RequestBody User user) {
        return backOfficeService.updateUsers(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable("id") Long id) {
        return backOfficeService.deleteUsers(id);
    }

}

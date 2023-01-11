package transevilz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dto.MessageResponse;
import transevilz.domain.dto.UserDTO;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

//    @GetMapping("/alluser")
//    public ResponseEntity<MessageResponse> getUser() {
//        return authService.getUser();
//    }
    @GetMapping("/alluser")
    public ResponseEntity<UserDTO> getUser() {
        return authService.getUser();
    }

}

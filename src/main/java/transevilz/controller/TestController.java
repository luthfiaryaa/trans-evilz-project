package transevilz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dao.Role;
import transevilz.domain.dao.User;
import transevilz.domain.dto.MessageResponse;
import transevilz.domain.dto.PinRequest;
import transevilz.domain.dto.UserDTO;
import transevilz.repository.RoleRepository;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;
import transevilz.services.UserDetailsImpl;
//import transevilz.util.TokenUtils;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {


    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/test")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/userss")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
    public ResponseEntity coba(){
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

       // String email = TokenUtils.claimToAppUser(TokenUtils.getClaim("evilprojectSecretKey"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }


//    @PostMapping("/users")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<?> createPin(){
//        String email = TokenUtils.claimToAppUser(TokenUtils.getClaim("Authorization"));
//        Optional<User> user = userRepository.findByEmail(email);

//        if (!user.isEmpty()) {
//        user.get().setMpin(pinRequest.getMpin());
//        userRepository.save(user.get());
//        return new ResponseEntity<>(MessageResponse.builder().message("pin create").build(), HttpStatus.OK);
//            } else {
//        return new ResponseEntity<>(MessageResponse.builder().message("pin already").build(), HttpStatus.BAD_REQUEST);
//        }
//        if (!user.isEmpty()){
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(MessageResponse.builder().message("failed").build(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PostMapping("/users/pin")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<?> createPinaa(@Valid @RequestBody PinRequest pinRequest){
//        String email = TokenUtils.claimToAppUser(TokenUtils.getClaim("Authorization"));
//        Optional<User> user = userRepository.findByEmail(email);
//       // Optional<User> users = userRepository.findByMpin(pinRequest.getMpin());
//
//        if (!users.isEmpty()) {
//        users.get().setMpin(pinRequest.getMpin());
//        userRepository.save(users.get());
//        return new ResponseEntity<>(MessageResponse.builder().message("pin create").build(), HttpStatus.OK);
//            } else {
//        return new ResponseEntity<>(MessageResponse.builder().message("pin already").build(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/usera")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> getName(Authentication authentication, @RequestBody PinRequest pinRequest) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findByEmail(userPrincipal.getEmail());

        if (!user.isEmpty() && userPrincipal.getMpin() == null) {
            user.get().setMpin(pinRequest.getMpin());
            userRepository.save(user.get());
            return new ResponseEntity<>(MessageResponse.builder().message("pin create").status("success").build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageResponse.builder().message("pin already").status("failed").build(), HttpStatus.BAD_REQUEST);
        }

    }
}
//}

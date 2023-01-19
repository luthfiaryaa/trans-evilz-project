package transevilz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dto.MessageResponse;
import transevilz.domain.dto.PasswordRequest;
import transevilz.repository.UserRepository;
import transevilz.services.ForgetPasswordService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        return forgetPasswordService.forgetPassword(passwordRequest);
    }

    @PostMapping("/new_password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        return forgetPasswordService.resetPassword(passwordRequest);
    }

}


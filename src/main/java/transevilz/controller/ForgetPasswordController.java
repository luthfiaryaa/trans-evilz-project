package transevilz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dto.PasswordRequest;
import transevilz.repository.UserRepository;
import transevilz.services.ForgetPasswordService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api1/forget")
public class ForgetPasswordController {


    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        return forgetPasswordService.forgetPassword(passwordRequest);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordRequest passwordRequest) {
        return forgetPasswordService.resetPassword(passwordRequest);
    }
}

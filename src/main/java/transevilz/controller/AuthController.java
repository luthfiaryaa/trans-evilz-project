package transevilz.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import transevilz.domain.dao.User;
import transevilz.domain.dto.*;
import transevilz.repository.BankRepository;
import transevilz.repository.TargetRepository;
import transevilz.repository.UserRepository;
import transevilz.services.AuthService;
import transevilz.services.BackOfficeService;
import transevilz.services.TransactionService;
import transevilz.services.UserDetailsImpl;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")

public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BackOfficeService backOfficeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse servletResponse) {
        return authService.authenticateUser(loginRequest, servletResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/pin")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> createPin(Authentication authentication, @RequestBody PinRequest pinRequest) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findByEmail(userPrincipal.getEmail());

        if (!user.isEmpty() && userPrincipal.getMpin() == null) {
            user.get().setMpin(pinRequest.getMpin());
            userRepository.save(user.get());
            return new ResponseEntity<>(MessageResponse.builder().message("pin created").status("success").build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageResponse.builder().message("user already have pin").status("failed").build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/new_pin")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> newPin(Authentication authentication, @RequestBody PinRequest pinRequest) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findByEmail(userPrincipal.getEmail());

        if (!user.isEmpty())
            user.get().setMpin(pinRequest.getMpin());
            userRepository.save(user.get());
            return new ResponseEntity<>(MessageResponse.builder().message("pin updated").status("success").build(), HttpStatus.OK);

    }

    @PostMapping("/otp_verification")
    public ResponseEntity<Object> otp(@Valid @RequestBody OTPRequest otpRequest){
        String otp = otpRequest.getOtpcode();
        if (otp.equals("102030")){
            return new ResponseEntity<>(MessageResponse.builder().message("otp verification success!").status("success").build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(MessageResponse.builder().message("otp verification error!").status("failed").build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/receipent")
    public ResponseEntity<Object> getTargetA(String search) {
        return authService.getTargetA(search);
    }

    @GetMapping("/bank")
    public ResponseEntity<Object> getBank() {
        return transactionService.getBank();
    }

}


package transevilz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transevilz.domain.dao.User;
import transevilz.domain.dto.MessageResponse;
import transevilz.domain.dto.PasswordRequest;
import transevilz.repository.RoleRepository;
import transevilz.repository.UserRepository;

import java.util.Optional;

@Service
public class ForgetPasswordService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> forgetPassword(PasswordRequest passwordRequest) {

        if (!userRepository.existsByEmail(passwordRequest.getEmail())){
            return new ResponseEntity<>(MessageResponse.builder().message("EMAIL_NOT_FOUND").build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(MessageResponse.builder().message("SUCCESS").build(), HttpStatus.OK);

    }

    public ResponseEntity<?> resetPassword(PasswordRequest passwordRequest) {
        Optional<User> user = userRepository.findByEmail(passwordRequest.getEmail());

       if (user.isEmpty()){
    //    if (!user.isPresent()){
            return new ResponseEntity<>(MessageResponse.builder().message("EMAIL_NOT_FOUND").build(), HttpStatus.BAD_REQUEST);
        }
        user.get().setPassword(encoder.encode(passwordRequest.getPassword()));
        userRepository.save(user.get());
        return new ResponseEntity<>(MessageResponse.builder().message("NEW_PASSWORD_SUCCESS").build(), HttpStatus.OK);
    }


}

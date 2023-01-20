package transevilz.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transevilz.domain.dao.*;
import transevilz.domain.dto.*;
import transevilz.repository.BankRepository;
import transevilz.repository.RoleRepository;
import transevilz.repository.TargetRepository;
import transevilz.repository.UserRepository;
import transevilz.jwt.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${evil.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public ResponseEntity<?> authenticate(Authentication authentication, HttpServletResponse servletResponse) {

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        servletResponse.addHeader("token_access", userDetails.getPassword());

        return new ResponseEntity<>(JwtResponse.builder()
                        .accessToken(jwt)
                        .expiredIn(String.valueOf(jwtExpirationMs))
                        .user(UserDTO.builder()
                                .id(userDetails.getId())
                                .email(userDetails.getEmail())
                                .fullname(userDetails.getFullname())
                                .mpinFlag(Objects.isNull(userDetails.getMpin()) ? false : true)
                                .build())
                        .build(), HttpStatus.OK
                );
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest, HttpServletResponse servletResponse) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        return this.authenticate(authentication, servletResponse);
    }

    public ResponseEntity<?> authenticateAdmin(LoginRequest loginRequest, HttpServletResponse servletResponse) {
        log.info(loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        return this.authenticate(authentication, servletResponse);
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(MessageResponse.builder().message("email already registered").build(), HttpStatus.BAD_REQUEST);
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                signUpRequest.getDoc_type(),
                signUpRequest.getDoc_number(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getBirth_place(),
                signUpRequest.getBirth_date(),
                signUpRequest.getAddress(),
                signUpRequest.getPhone_number(),
                encoder.encode( signUpRequest.getPassword()),
                signUpRequest.getSex());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(MessageResponse.builder().message("user created").build(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getTargetA(String search) {
        Optional<Target> targets = targetRepository.searcha(search);
        Target target1 = targets.get();
        return new ResponseEntity<>(TargetResponse.builder().name(target1.getName()).build(), HttpStatus.OK);
    }

}

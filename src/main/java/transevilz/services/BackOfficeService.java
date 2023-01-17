package transevilz.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import transevilz.domain.dao.ERole;
import transevilz.domain.dao.Role;
import transevilz.domain.dao.User;
import transevilz.domain.dto.BackOfficeUserResponseDTO;
import transevilz.domain.dto.FindUserDTO;
import transevilz.domain.dto.MessageResponse;
import transevilz.domain.dto.SignupRequest;
import transevilz.repository.RoleRepository;
import transevilz.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BackOfficeService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public ResponseEntity<?> addUser(SignupRequest signUpRequest) {

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
                encoder.encode(signUpRequest.getPassword()),
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

    public ResponseEntity<Object> getUser() {
        List<User> users = userRepository.findAll();
        List<FindUserDTO> userDTOList = new ArrayList<>();
        for (User user : users){
            FindUserDTO item = FindUserDTO.builder().id(user.getId()).firstname(user.getLastname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .doc_type(user.getDoc_type())
                    .build();
            userDTOList.add(item);
        }
        List<FindUserDTO> sortList = userDTOList.stream().sorted((o1, o2)->o1.getId().
                        compareTo(o2.getId())).
                collect(Collectors.toList());

        BackOfficeUserResponseDTO backOfficeUserResponseDTO = new BackOfficeUserResponseDTO();
        BackOfficeUserResponseDTO responseBackOffice = BackOfficeUserResponseDTO.builder().users(sortList).build();
//        BackOfficeUserResponseDTO responseBackOffice = BackOfficeUserResponseDTO.builder().users(sortlist).build();

        return new ResponseEntity<>(responseBackOffice, HttpStatus.OK);
    }

    public User getUserId(Long id){
        return userRepository.findById(id).get();
    }

    public ResponseEntity<User> updateUsers(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User users = userOptional.get();
            users.setFirstname(user.getFirstname());
            users.setSex(user.getSex());
            return new ResponseEntity<>(userRepository.save(users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteUsers(Long id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(MessageResponse.builder().message("user deleted").status("success").build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageResponse.builder().message("user not found").status("failed").build(), HttpStatus.BAD_REQUEST);
    }

    public static Specification<User> getUserName(String firstname, String email){
        return ((root, query, criteriaBuilder) ->{
            List<Predicate> predicate = new ArrayList<>();

            if (firstname != null && !(firstname.isEmpty())){
                predicate.add(criteriaBuilder.equal(root.get("firstname"),firstname));
            }

            if (email != null && !(email.isEmpty())){
                predicate.add(criteriaBuilder.equal(root.get("email"),email));
            }

            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        });
    }


    public List<User> getProductByName(String search){
        return userRepository.search(search);
    }
}

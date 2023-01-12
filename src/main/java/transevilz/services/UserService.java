package transevilz.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import transevilz.domain.dao.User;
import transevilz.domain.dto.UserDTO;
import transevilz.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        log.info(value);
        User user = userRepository.findUserByUsernameOrByEmail(value)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + value));

        return UserDetailsImpl.build(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}
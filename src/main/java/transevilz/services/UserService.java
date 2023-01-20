package transevilz.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import transevilz.domain.dao.User;
import transevilz.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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

    public List<User> getUserList(){
        String roleName = "ROLE_USER";
        return userRepository.getUserList(roleName);
    }
}
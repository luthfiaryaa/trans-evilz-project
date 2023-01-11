package transevilz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    //List<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE username=?1 or email=?1", nativeQuery = true)
    Optional<User> findUserByUsernameOrByEmail(String value);

//    Optional<User> findByUsername(String username);
//    Optional<User> findByUsernameAndPassword(String username, String password);
//    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
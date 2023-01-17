package transevilz.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.User;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE username=?1 or email=?1", nativeQuery = true)
    Optional<User> findUserByUsernameOrByEmail(String value);

    @Query(value = "SELECT * FROM users WHERE first_name LIKE %?1% OR last_name LIKE %?1%", nativeQuery = true)
    List<User> search(String search);

    Boolean existsByEmail(String email);

    List<User> findAll(Specification<User> specification);
}

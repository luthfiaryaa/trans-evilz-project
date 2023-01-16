package transevilz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//    @Query(value = "SELECT u FROM users u WHERE u.firstname LIKE :firstname", nativeQuery = true)
//    List<User> findName(@PathParam("firstname") String firstname);

    Boolean existsByEmail(String email);

    List<User> findAll(Specification<User> specification);
}

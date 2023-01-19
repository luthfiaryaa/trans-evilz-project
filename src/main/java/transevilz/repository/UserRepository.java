package transevilz.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean findByMpin(String mpin);

    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE username=?1 or email=?1", nativeQuery = true)
    Optional<User> findUserByUsernameOrByEmail(String value);

    @Query(value = "SELECT DISTINCT * FROM users, user_roles WHERE user_roles.role_id = 2 ", nativeQuery = true)
    List<User> findAllUser();

    @Query(value = "SELECT * FROM users WHERE first_name LIKE %?1% OR last_name LIKE %?1%", nativeQuery = true)
    List<User> search(String search);

    Boolean existsByEmail(String email);
    //Boolean existByPin(String mpin);
    List<User> findAll(Specification<User> specification);
}

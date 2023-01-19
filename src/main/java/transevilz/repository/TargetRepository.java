package transevilz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.Bank;
import transevilz.domain.dao.Target;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

    @Query(value = "SELECT * FROM target WHERE no_rekening = ?1", nativeQuery = true)
    List<Target> search(String search);

    @Query(value = "SELECT * FROM target WHERE no_rekening = ?1", nativeQuery = true)
    Optional<Target> searcha(String search);
}

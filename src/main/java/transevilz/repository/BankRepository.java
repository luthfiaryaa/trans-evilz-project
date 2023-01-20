package transevilz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}

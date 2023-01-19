package transevilz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

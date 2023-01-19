package transevilz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import transevilz.domain.dao.Bank;
import transevilz.domain.dao.User;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {


}

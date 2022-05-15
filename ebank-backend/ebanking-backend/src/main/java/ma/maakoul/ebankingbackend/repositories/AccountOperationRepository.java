package ma.maakoul.ebankingbackend.repositories;

import ma.maakoul.ebankingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, String> {
     //List<AccountOperation> findByBankAccountId
}

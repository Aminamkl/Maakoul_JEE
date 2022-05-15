package ma.maakoul.ebankingbackend.repositories;

import ma.maakoul.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}

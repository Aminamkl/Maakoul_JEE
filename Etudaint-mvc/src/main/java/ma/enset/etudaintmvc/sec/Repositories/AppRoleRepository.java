package ma.enset.etudaintmvc.sec.Repositories;

import ma.enset.etudaintmvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
   AppRole findByRoleName(String roleName);
}

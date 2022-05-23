package ma.maakoul_amina.exam_jee.sec.Repositories;

import ma.maakoul_amina.exam_jee.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
   AppRole findByRoleName(String roleName);
}

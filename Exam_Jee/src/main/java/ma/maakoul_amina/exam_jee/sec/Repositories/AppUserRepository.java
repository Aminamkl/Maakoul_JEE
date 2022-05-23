package ma.maakoul_amina.exam_jee.sec.Repositories;


import ma.maakoul_amina.exam_jee.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}

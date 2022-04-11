package ma.enset.etudaintmvc;

import ma.enset.etudaintmvc.entities.Etudiant;
import ma.enset.etudaintmvc.entities.Genre;
import ma.enset.etudaintmvc.repositories.EtudiantRepository;
import ma.enset.etudaintmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class EtudaintMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudaintMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(EtudiantRepository patientRepository){
        return args -> {
            patientRepository.save(new Etudiant(null,"Maakoul","Amina","amina@gmail.com",new Date(), Genre.FEMININ,true));
            patientRepository.save(new Etudiant(null,"Wahid","Ali","ali@gmail.com",new Date(), Genre.MASCULIN,false));
            patientRepository.save(new Etudiant(null,"Maakoul","Zineb","zineb@gmail.com",new Date(), Genre.FEMININ,true));
            patientRepository.save(new Etudiant(null,"Abidi","Houda","houda@gmail.com",new Date(), Genre.FEMININ,false));
            patientRepository.save(new Etudiant(null,"Karam","Ahmed","ahmed@gmail.com",new Date(), Genre.MASCULIN,false));
            patientRepository.save(new Etudiant(null,"Jalal","Bilal","bilal@gmail.com",new Date(), Genre.MASCULIN,true));
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Ahmed","1234","1234");
            securityService.saveNewUser("Amina","1234","1234");
            securityService.saveNewUser("Zineb","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Amina","USER");
            securityService.addRoleToUser("Amina","ADMIN");
            securityService.addRoleToUser("Ahmed","USER");
            securityService.addRoleToUser("Zineb","USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

package ma.enset.patientmvc;

import ma.enset.patientmvc.entities.Patient;
import ma.enset.patientmvc.repositories.PatientRepository;
import ma.enset.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "Ali",new Date(),false,12));
            patientRepository.save(new Patient(null, "Ahmed",new Date(),false,15));
            patientRepository.save(new Patient(null, "Houda",new Date(),false,20));
            patientRepository.save(new Patient(null, "Saad",new Date(),false,12));
            patientRepository.save(new Patient(null, "Hassan",new Date(),false,15));
            patientRepository.save(new Patient(null, "Chaimaa",new Date(),false,20));
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("amina","1234","1234");
            securityService.saveNewUser("zineb","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("amina","USER");
            securityService.addRoleToUser("amina","ADMIN");
            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("zineb","USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

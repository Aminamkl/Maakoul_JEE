package ma.enset.patientmvc;

import ma.enset.patientmvc.entities.Patient;
import ma.enset.patientmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            /*patientRepository.save(new Patient(null, "Ali",new Date(),false,12));
            patientRepository.save(new Patient(null, "Ahmed",new Date(),false,15));
            patientRepository.save(new Patient(null, "Houda",new Date(),false,20));
            patientRepository.save(new Patient(null, "Saad",new Date(),false,12));
            patientRepository.save(new Patient(null, "Hassan",new Date(),false,15));
            patientRepository.save(new Patient(null, "Chaimaa",new Date(),false,20));*/
        };
    }
}

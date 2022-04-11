package ma.enset.etudaintmvc;

import ma.enset.etudaintmvc.entities.Etudiant;
import ma.enset.etudaintmvc.entities.Genre;
import ma.enset.etudaintmvc.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}

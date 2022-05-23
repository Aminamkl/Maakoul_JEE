package ma.maakoul_amina.exam_jee;

import ma.maakoul_amina.exam_jee.entities.*;
import ma.maakoul_amina.exam_jee.repositories.*;
import ma.maakoul_amina.exam_jee.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class ExamJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamJeeApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(CommentaireRepository commentaireRepository,
                                        SalleRepository salleRepository,
                                        SessionRepository sessionRepository,
                                        InscriptionRepository inscriptionRepository,
                                        ConférenceRepository conférenceRepository,
                                        ParticipantRepository participantRepository) {

        return args -> {
        /*
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
                Invité participant=new Invité();
                participant.setNom(name);
                participant.setEmail(name+"@gmail.com");
                participant.setGenre(Genre.FEMININ);
                participant.setPhoto(name+"_photo.ma");
                participant.setAffiliation("affilite_"+name);
                participantRepository.save(participant);
            });

            Stream.of("Ali","Amina","Zineb").forEach(name->{
                Modérateur participant=new Modérateur();
                participant.setNom(name);
                participant.setEmail(name+"@gmail.com");
                participant.setGenre(Genre.FEMININ);
                participant.setPhoto(name+"_photo.ma");
                participant.setSpécialité("Ingénieur");
                participantRepository.save(participant);
            });

            Stream.of("Hamid","Khalid","Nada").forEach(name->{
                Speaker participant=new Speaker();
                participant.setNom(name);
                participant.setEmail(name+"@gmail.com");
                participant.setGenre(Genre.FEMININ);
                participant.setPhoto(name+"_photo.ma");
                participant.setProfil_pro("profil_"+name);
                participantRepository.save(participant);
            });*/

            Salle salle = new Salle();
            salle.setNom("salle1");
            salleRepository.save(salle);

            Session session = new Session();
            session.setNom("Session 1");
            session.setSalle(salle);
            sessionRepository.save(session);


        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Ahmed","1234","1234");
            securityService.saveNewUser("Amina","1234","1234");
            securityService.saveNewUser("Zineb","1234","1234");

            securityService.saveNewRole("ROLE_CONFERENCIER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Amina","ROLE_INVITE");
            securityService.addRoleToUser("Amina","ADMIN");
            securityService.addRoleToUser("Ahmed","ROLE_MODERATEUR");
            securityService.addRoleToUser("Zineb","ROLE_CONFERENCIER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
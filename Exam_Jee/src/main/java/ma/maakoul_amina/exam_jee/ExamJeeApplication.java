package ma.maakoul_amina.exam_jee;

import ma.maakoul_amina.exam_jee.entities.*;
import ma.maakoul_amina.exam_jee.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ExamJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamJeeApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CommentaireRepository commentaireRepository,
                                        SalleRepository salleRepository,
                                        SessionRepository sessionRepository,
                                        InscriptionRepository inscriptionRepository,
                                        ConférenceRepository conférenceRepository,
                                        ParticipantRepository participantRepository) {
        return args -> {
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
            });

            Salle salle= new Salle();
            salle.setNom("salle1");

            Session session=new Session();
            session.setNom("Session 1");
            session.setSalle(salle);


        };
    }
}

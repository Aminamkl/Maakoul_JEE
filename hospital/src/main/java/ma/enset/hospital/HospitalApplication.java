package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            MedecinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository){
        return args -> {
            Stream.of("Mohamed","Hamid", "Amina").forEach(name->{
                Patient patient=new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                hospitalService.savePatient(patient);
            });
            Stream.of("Ali","Zineb", "Walid").forEach(name->{
                Medecin medecin=new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                hospitalService.saveMedecin(medecin);
            });

            Patient patient=patientRepository.findById(1L).orElse(null);
            Patient patient1= patientRepository.findByNom("Hamid");

            Medecin medecin=medecinRepository.findByNom("Zineb");
            RendezVous rendezVous=new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            RendezVous savedRDV = hospitalService.saveRDV(rendezVous);
            System.out.println(savedRDV.getId());

            RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
            Consultation consultation=new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRapport("Rapport du consultation...");
            consultation.setRendezVous(rendezVous1);
            hospitalService.saveConsultation(consultation);

        };
    }
}

package ma.enset.prgjpa;

import ma.enset.prgjpa.entities.Patient;
import ma.enset.prgjpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PrgJpaApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrgJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<100; i++){
            patientRepository.save(new Patient(null,"hassan",new Date(),Math.random()>0.5?true:false, 100));
        }

        /*List<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        //System.out.println("Total pages :"+patients.getTotalPages());
        //System.out.println("Total element :"+patients.getTotalPages());
        //System.out.println("Total pages :"+patients.getTotalPages());
        patients.forEach(p->{
            System.out.println(p.getId());
        });*/

        Patient patient=patientRepository.findById(2L).orElse(null);
        if (patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
            System.out.println(patient.getScore());
        }
        patient.setScore(870);
        patientRepository.save(patient);
        //patientRepository.deleteById(1L);
    }

}

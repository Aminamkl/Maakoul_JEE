package ma.enset.prgjpa;

import ma.enset.prgjpa.entities.Patient;
import ma.enset.prgjpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
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


        /*List<Patient> patients = patientRepository.findAll();

        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        patients.forEach(p->{
            System.out.println(p.getNom()+ " " +p.getScore());
        });
        System.out.println("Total pages :"+patients.getTotalPages());
        System.out.println("Total element :"+patients.getTotalElements());
        System.out.println("Numero du page :"+patients.getNumber());
        List<Patient> content = patients.getContent();
        */
        //Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0,5));
        List<Patient> patientList=patientRepository.findByMaladeIsTrueAndScoreLessThan(40);
        //List<Patient> patientList=patientRepository.chercherPatients("%h%",40);
        patientList.forEach(p->{
            System.out.println("["+p.getId()+" , "+p.getNom()+" , "+p.isMalade()+" , "+p.getScore()+" , "+p.getDate_naiss()+"]");
        });

        System.out.println("********************************");

        Patient patient=patientRepository.findById(1L).orElse(null);
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

package ma.enset.prgjpa.repositories;

import ma.enset.prgjpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    public List<Patient> findByMalade(boolean  m);
    Page<Patient> findByMalade(boolean  m, Pageable pageable);
    List<Patient> findByMaladeAndScoreLessThan(boolean m, int score);
    //List<Patient> chercherPatients(Date d1, Date d2, String nom);
}
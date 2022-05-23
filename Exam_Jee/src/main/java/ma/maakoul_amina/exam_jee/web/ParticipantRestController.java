package ma.maakoul_amina.exam_jee.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.maakoul_amina.exam_jee.entities.Participant;
import ma.maakoul_amina.exam_jee.repositories.ParticipantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@AllArgsConstructor
public class ParticipantRestController {
    private ParticipantRepository participantRepository;

    @GetMapping("/participants")
    public List<Participant> participants(){
        return participantRepository.findAll();
    }
    @GetMapping("/participants/search")
    public List<Participant> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return participantRepository.findByNomContains("%"+keyword+"%");
    }

    @GetMapping("/participants/{id}")
    public Optional<Participant> getParticipant(@PathVariable(name = "id") int customerId) {
        return participantRepository.findById(customerId);
    }

    @PostMapping("/participants")
    public Participant saveCustomer(@RequestBody Participant participant){
        return participantRepository.save(participant);
    }


}

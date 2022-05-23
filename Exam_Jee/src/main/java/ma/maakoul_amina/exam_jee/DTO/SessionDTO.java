package ma.maakoul_amina.exam_jee.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class SessionDTO {
    private int id;

    private String nom;

    private List<ConférenceDTO> conférences;
    private SalleDTO salle;
    private ParticipantDTO participant;

}

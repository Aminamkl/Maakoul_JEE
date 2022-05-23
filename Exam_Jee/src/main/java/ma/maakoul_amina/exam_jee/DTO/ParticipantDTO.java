package ma.maakoul_amina.exam_jee.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maakoul_amina.exam_jee.entities.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Data

public abstract class ParticipantDTO {
    private int id;

    private String nom;

    private String email;
    private String photo;
    private Genre genre;
    private List<CommentaireDTO> commentaireList;

    private List<ConférenceDTO> conférences;

    private List<SessionDTO> sessions;

    private List<InscriptionDTO> inscriptions;
}

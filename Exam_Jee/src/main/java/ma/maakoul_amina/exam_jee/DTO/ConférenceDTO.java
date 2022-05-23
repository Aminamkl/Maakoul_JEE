package ma.maakoul_amina.exam_jee.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
public class ConférenceDTO {
    private int id;
    private String title;
    private Date date;
    private String heure_fin;
    private String heure_débue;
    private String description;
    private List<CommentaireDTO> commentaires;
    private SessionDTO session;

    private ParticipantDTO participant;
}

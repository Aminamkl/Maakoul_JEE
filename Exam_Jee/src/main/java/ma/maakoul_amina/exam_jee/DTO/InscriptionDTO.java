package ma.maakoul_amina.exam_jee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maakoul_amina.exam_jee.entities.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data

public class InscriptionDTO {
    private int id;

    private Date date;

    private Status status;

    private ParticipantDTO participant;
}

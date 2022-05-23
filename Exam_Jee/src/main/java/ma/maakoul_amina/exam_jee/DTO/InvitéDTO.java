package ma.maakoul_amina.exam_jee.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
public class InvitéDTO extends ParticipantDTO {
    private String affiliation;
}

package ma.maakoul_amina.exam_jee.DTO;

import lombok.Data;

import java.util.Date;


@Data
public class CommentaireDTO {
    private int id;
    private Date date;
    private String contenu;
    private int nombre_like;

    private ConférenceDTO conférence;

    private ParticipantDTO participant;
}

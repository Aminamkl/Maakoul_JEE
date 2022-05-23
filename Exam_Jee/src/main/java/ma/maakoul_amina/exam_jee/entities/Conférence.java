package ma.maakoul_amina.exam_jee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conférence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String heure_fin;
    private String heure_débue;
    private String description;
    @OneToMany(mappedBy = "conférence")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Commentaire> commentaires;

    @ManyToOne
    private Session session;

    @ManyToOne
    private Participant participant;
}

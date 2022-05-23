package ma.maakoul_amina.exam_jee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Size(min=3, max=70)
    private String nom;

    @OneToMany(mappedBy = "session")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Conférence> conférences;

    @ManyToOne
    private Salle salle;

    @ManyToOne
    private Participant participant;

}

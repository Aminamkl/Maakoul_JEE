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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 10)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min=3, max=70)
    private String nom;

    @NotEmpty
    @Size(min=5, max=70)
    private String email;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "participant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Commentaire> commentaireList;

    @OneToMany(mappedBy = "participant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Conférence> conférences;

    @OneToMany(mappedBy = "participant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Session> sessions;

    @OneToMany(mappedBy = "participant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Inscription> inscriptions;
}

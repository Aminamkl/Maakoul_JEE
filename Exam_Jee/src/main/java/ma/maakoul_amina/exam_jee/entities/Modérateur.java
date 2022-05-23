package ma.maakoul_amina.exam_jee.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Modérateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modérateur extends Participant {
    private String spécialité;
}

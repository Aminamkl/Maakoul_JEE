package ma.maakoul.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;


@DiscriminatorValue("SA")
@Data
@NoArgsConstructor @AllArgsConstructor
public class SavingBankAccountDTO extends AccountDTO {
    private double interestRate;
}
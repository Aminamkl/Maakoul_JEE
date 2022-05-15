package ma.maakoul.ebankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maakoul.ebankingbackend.entities.BankAccount;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("CA")
@Data
@NoArgsConstructor @AllArgsConstructor
public class CurrentBankAccountDTO extends BankAccount{
    private double overDraft;
}

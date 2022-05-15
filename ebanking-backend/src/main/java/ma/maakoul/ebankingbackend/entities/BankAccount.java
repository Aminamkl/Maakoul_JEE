package ma.maakoul.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.maakoul.ebankingbackend.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 5, discriminatorType = DiscriminatorType.STRING)
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}

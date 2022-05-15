package ma.maakoul.ebankingbackend;

import ma.maakoul.ebankingbackend.entities.*;
import ma.maakoul.ebankingbackend.enums.AccountStatus;
import ma.maakoul.ebankingbackend.enums.OperationType;
import ma.maakoul.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.maakoul.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.maakoul.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.maakoul.ebankingbackend.repositories.AccountOperationRepository;
import ma.maakoul.ebankingbackend.repositories.BankAccountRepository;
import ma.maakoul.ebankingbackend.repositories.CustomerRepository;
import ma.maakoul.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Amina", "Hasnaa", "Achraf").forEach(name->{
                Customer client = new Customer();
                client.setName(name);
                client.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(client);
            });
            bankAccountService.listCustomer().forEach(client->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000, 9000, client.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000, 5.5, client.getId());
                    List<BankAccount> accounts = bankAccountService.bankAccountList();
                    for (BankAccount account:accounts){
                        for (int i = 0; i < 10; i++) {
                            bankAccountService.credit(account.getId(), 10000+Math.random()*120000, "Credit");
                            bankAccountService.debit(account.getId(), 1000+Math.random()*9000, "Debit");
                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository clientRepository,
                            BankAccountRepository accountRepository,
                            AccountOperationRepository operationRepository){
        return args -> {
            Stream.of("Amina", "Hasnaa", "Achraf").forEach(name->{
                Customer client = new Customer();
                client.setName(name);
                client.setEmail(name+"@gmail.com");
                clientRepository.save(client);
            });
            clientRepository.findAll().forEach(client -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(client);
                currentAccount.setOverDraft(9000);
                accountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(client);
                savingAccount.setInterestRate(5.5);
                accountRepository.save(savingAccount);

            });

            accountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation operation = new AccountOperation();
                    operation.setOperationDate(new Date());
                    operation.setAmount(Math.random()*12000);
                    operation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                    operation.setAccount(account);
                    operationRepository.save(operation);
                }
            });

        };
    }
}


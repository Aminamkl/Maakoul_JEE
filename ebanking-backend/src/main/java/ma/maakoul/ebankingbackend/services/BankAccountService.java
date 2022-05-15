package ma.maakoul.ebankingbackend.services;

import ma.maakoul.ebankingbackend.dtos.CustomerDTO;
import ma.maakoul.ebankingbackend.entities.BankAccount;
import ma.maakoul.ebankingbackend.entities.Customer;
import ma.maakoul.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.maakoul.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.maakoul.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomer();
    BankAccount getBankAccount(String accounId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException , BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();
}

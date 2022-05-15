package ma.maakoul.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.maakoul.ebankingbackend.dtos.CustomerDTO;
import ma.maakoul.ebankingbackend.entities.*;
import ma.maakoul.ebankingbackend.enums.OperationType;
import ma.maakoul.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.maakoul.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.maakoul.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.maakoul.ebankingbackend.mappers.BankAccountMapperImpl;
import ma.maakoul.ebankingbackend.repositories.AccountOperationRepository;
import ma.maakoul.ebankingbackend.repositories.BankAccountRepository;
import ma.maakoul.ebankingbackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtomapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving new Customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedCurrentAccount = bankAccountRepository.save(currentAccount);
        return savedCurrentAccount;
    }

    @Override
    public BankAccount saveSavingBankAccount(double initialBalance, double interestRate, Long clientId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(clientId).orElse(null);
        if (customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedSavingAccount = bankAccountRepository.save(savingAccount);
        return savedSavingAccount;
    }

    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> clients = customerRepository.findAll();
        List<CustomerDTO> clientsDTO = clients.stream().map(cust -> dtomapper.fromClient(cust)).collect(Collectors.toList());
        return clientsDTO;
    }

    @Override
    public BankAccount getBankAccount(String accounId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accounId).orElseThrow(()->new BankAccountNotFoundException("Account not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount=getBankAccount(accountId);
        if(bankAccount.getBalance()<amount){
            throw new BalanceNotSufficientException("Balance not Sufficient");
        }
        AccountOperation operation = new AccountOperation();
        operation.setType(OperationType.DEBIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setAccount(bankAccount);
        accountOperationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount account = getBankAccount(accountId);
        AccountOperation operation = new AccountOperation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setAccount(account);
        accountOperationRepository.save(operation);
        account.setBalance(account.getBalance()+amount);
        bankAccountRepository.save(account);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to "+ accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }

    @Override
    public List<BankAccount> bankAccountList(){
        return null;
    }

    public CustomerDTO getCustomer(Long customerId){
        customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer Not found"));
         return null;
    }

    //public CustomerDTO save
}

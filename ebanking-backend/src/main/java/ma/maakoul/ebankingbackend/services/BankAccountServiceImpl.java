package ma.maakoul.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.maakoul.ebankingbackend.dtos.*;
import ma.maakoul.ebankingbackend.entities.*;
import ma.maakoul.ebankingbackend.enums.OperationType;
import ma.maakoul.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.maakoul.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.maakoul.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.maakoul.ebankingbackend.mappers.BankAccountMapperImpl;
import ma.maakoul.ebankingbackend.repositories.AccountOperationRepository;
import ma.maakoul.ebankingbackend.repositories.BankAccountRepository;
import ma.maakoul.ebankingbackend.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer =dtomapper.fromClientDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtomapper.fromClient(savedCustomer);
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
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
        return dtomapper.fromCurrentAccount(savedCurrentAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long clientId) throws CustomerNotFoundException {
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
        return dtomapper.fromSavingAccount(savedSavingAccount);
    }

    @Override
    public List<CustomerDTO> listCustomer() {
        List<Customer> clients = customerRepository.findAll();
        List<CustomerDTO> clientsDTO = clients.stream().map(cust -> dtomapper.fromClient(cust)).collect(Collectors.toList());
        return clientsDTO;
    }

    @Override
    public AccountDTO getBankAccount(String accounId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accounId).orElseThrow(()->new BankAccountNotFoundException("Account not found"));
        if(bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return dtomapper.fromSavingAccount(savingAccount);
        }
        else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return dtomapper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow( () -> new BankAccountNotFoundException("BankAccount not found"));
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
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow( () -> new BankAccountNotFoundException("BankAccount not found"));
        AccountOperation operation = new AccountOperation();
        operation.setType(OperationType.CREDIT);
        operation.setAmount(amount);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setAccount(bankAccount);
        accountOperationRepository.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to "+ accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }

    @Override
    public List<AccountDTO> bankAccountList() {

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        return bankAccounts.stream().map(bankAccount -> {

            if(bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return dtomapper.fromSavingAccount(savingAccount);
            }
            else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return dtomapper.fromCurrentAccount(currentAccount);
            }

        }).collect(Collectors.toList());

    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException{
        Customer customer= customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer Not found"));
        return dtomapper.fromClient(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("update customer");
        Customer customer = dtomapper.fromClientDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return dtomapper.fromClient(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);

    }

    @Override
    public List<OperationDTO> historique(String accountId) {
        /*List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream()
                .map(op -> dtomapper.fromOperation(op))
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {

        /*BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) {
            throw new BankAccountNotFoundException("Bank Account Not Found");
        }
        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<OperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtomapper.fromOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;*/
        return null;
    }

}

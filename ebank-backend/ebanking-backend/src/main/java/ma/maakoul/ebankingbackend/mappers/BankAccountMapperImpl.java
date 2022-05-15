package ma.maakoul.ebankingbackend.mappers;

import ma.maakoul.ebankingbackend.dtos.*;
import ma.maakoul.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    //Account mapper
    public AccountDTO fromAccount(BankAccount account){
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        return accountDTO;
    }
//    public Account fromAccountDTO(AccountDTO accountDTO){
//        Account account = new Account();
//        BeanUtils.copyProperties(accountDTO, account);
//        return account;
//    }

    //Client mapper
    public CustomerDTO fromClient(Customer client){
        CustomerDTO clientDTO = new CustomerDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }
    public Customer fromClientDTO(CustomerDTO clientDTO){
        Customer client = new Customer();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    //operation mapper
    public OperationDTO fromOperation(AccountOperation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation, operationDTO);
        return operationDTO;
    }
    public AccountOperation fromOperationDTO(OperationDTO operationDTO){
        AccountOperation operation = new AccountOperation();
        BeanUtils.copyProperties(operationDTO, operation);
        return operation;
    }

    //CurrentAccount mapper
    public CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        return currentAccountDTO;
    }
    public CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        return currentAccount;
    }

    //SavingAccount mapper
    public SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        return savingAccountDTO;
    }
    public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingAccountDTO){
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO, savingAccount);
        return savingAccount;
    }
}

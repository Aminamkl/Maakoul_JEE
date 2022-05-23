package ma.maakoul.ebankingbackend.web;

import ma.maakoul.ebankingbackend.dtos.AccountDTO;
import ma.maakoul.ebankingbackend.dtos.AccountHistoryDTO;
import ma.maakoul.ebankingbackend.dtos.OperationDTO;
import ma.maakoul.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.maakoul.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/account/{accountId}")
    public AccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException, BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<AccountDTO> listAccount()  {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/operations/{accountId}")
    public List<OperationDTO> getHistorique(@PathVariable String accountId){
        return bankAccountService.historique(accountId);
    }

    @GetMapping("/pageOperations/{accountId}")
    public AccountHistoryDTO getAccountHistorique(
            @PathVariable String accountId,
            @RequestParam(name ="page", defaultValue = "0") int page,
            @RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException{

        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}

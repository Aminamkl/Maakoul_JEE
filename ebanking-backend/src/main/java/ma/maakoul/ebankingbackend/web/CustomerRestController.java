package ma.maakoul.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.maakoul.ebankingbackend.dtos.CustomerDTO;
import ma.maakoul.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomer();
    }

    //@GetMapping("/customers/{id}")

    //@PutMapping("/customers/{customerId}")

}

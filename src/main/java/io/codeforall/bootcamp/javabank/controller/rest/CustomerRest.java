package io.codeforall.bootcamp.javabank.controller.rest;

import io.codeforall.bootcamp.javabank.command.AccountDto;
import io.codeforall.bootcamp.javabank.command.CustomerDto;
import io.codeforall.bootcamp.javabank.converters.AccountToAccountDto;
import io.codeforall.bootcamp.javabank.converters.CustomerDtoToCustomer;
import io.codeforall.bootcamp.javabank.converters.CustomerToCustomerDto;
import io.codeforall.bootcamp.javabank.persistence.model.Customer;
import io.codeforall.bootcamp.javabank.persistence.model.account.Account;
import io.codeforall.bootcamp.javabank.services.AccountService;
import io.codeforall.bootcamp.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerToCustomerDto toDto;

    @Autowired
    private CustomerDtoToCustomer toCustomer;

    @Autowired
    private AccountToAccountDto toAccountDto;

    @Autowired
    private AccountService accountService;



    @RequestMapping(method = RequestMethod.GET, value = "/customer")
    public ResponseEntity<List<CustomerDto>> listResponseEntity() {

        List<CustomerDto> customerDtoList = toDto.convert(customerService.list());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{id}")
    public  ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer id) {

      Customer customer = customerService.get(id);

      CustomerDto customerDto = toDto.convert(customer);

      return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{id}/account")
    public  ResponseEntity<List<AccountDto>> getCustomerAccounts(@PathVariable Integer id) {

        Customer customer = customerService.get(id);
        List<Account> accounts = customer.getAccounts();
        List<AccountDto> accountList = toAccountDto.convert(accounts);

        return new ResponseEntity<List<AccountDto>>(accountList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{cid}/account/{aid}")
    public ResponseEntity<AccountDto> getSpecificAccount(@PathVariable Integer aid, @PathVariable Integer cid) {

        Customer customer = customerService.get(cid);
        Account account = accountService.get(aid);
        AccountDto accountDto = toAccountDto.convert(account);

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
}




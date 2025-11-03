package io.codeforall.bootcamp.javabank.domain.controller;

import io.codeforall.bootcamp.javabank.domain.model.Bank;
import io.codeforall.bootcamp.javabank.domain.model.Customer;
import io.codeforall.bootcamp.javabank.domain.view.LoginView;
import org.academiadecodigo.bootcamp.Prompt;

public class LoginController {

    private Bank bank;
    private LoginView view;

    public LoginController(Bank bank, Prompt prompt){
        this.bank = bank;
        this.view = new LoginView(prompt);
    }

    public Customer login() {
        int customerID = view.scanCustomerId(bank.getCustomerIds());
        return bank.getCustomer(customerID);
    }


}

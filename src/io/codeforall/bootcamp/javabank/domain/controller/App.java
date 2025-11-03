package io.codeforall.bootcamp.javabank.domain.controller;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.BankApplication;
import io.codeforall.bootcamp.javabank.domain.model.Bank;
import io.codeforall.bootcamp.javabank.domain.model.Customer;
import io.codeforall.bootcamp.javabank.domain.model.managers.AccountManager;
import org.academiadecodigo.bootcamp.Prompt;

public class App {

    private Prompt prompt;
    private Bank bank;

    public void start() {

        prompt = new Prompt(System.in, System.out);
        bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);


        LoginController loginController = new LoginController(bank, prompt);
        Customer customer = loginController.login();


    }

    public static void main(String[] args) {
            new App().start();
        }

}

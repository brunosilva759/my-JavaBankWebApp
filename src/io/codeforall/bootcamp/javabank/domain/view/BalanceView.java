package io.codeforall.bootcamp.javabank.domain.view;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.Messages;
import io.codeforall.bootcamp.javabank.domain.model.Customer;
import io.codeforall.bootcamp.javabank.domain.model.account.Account;

import java.text.DecimalFormat;

public class BalanceView {

    private static final DecimalFormat df = new DecimalFormat("#.##");


    public void showBalance(Customer customer) {
        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        for (Account account : customer.getAccounts()) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }

}




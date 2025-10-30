package io.codeforall.bootcamp.javabank.application;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.domain.account.Account;
import io.codeforall.bootcamp.javabank.domain.account.AccountType;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

import java.text.DecimalFormat;
import java.util.Set;

public class Operations {

    private Customer accessingCustomer;
    private Bank bank;
    private Prompt prompt;

    /**
     * Constructs a new {@code Operations} instance with the specified customer, bank, and prompt.
     *
     * @param accessingCustomer the customer currently using the application
     * @param bank              the bank instance to perform operations on
     * @param prompt            the prompt used for user input and output
     */
    public Operations(Customer accessingCustomer, Bank bank, Prompt prompt) {
        this.accessingCustomer = accessingCustomer;
        this.bank = bank;
        this.prompt = prompt;
    }

    /**
     * Displays the balance for all the accessing customer's accounts,
     * along with the total balance.
     */
    void showCustomerBalance() {

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("\n" + accessingCustomer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = accessingCustomer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(accessingCustomer.getBalance()));
    }

    /**
     * Creates a new checking account for the accessing customer.
     */
    void openAccount() {

        int accountId = accessingCustomer.openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + accessingCustomer.getName() + " : " + accountId);
    }

    /**
     * Deposit an amount into an account
     */
    void depositToCustomerAccount() {
        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());

        int accountId = scanAccount();
        double amount = scanAmount();

        if (accessingCustomer.getAccountIds().contains(accountId)) {
            bank.getAccountManager().deposit(accountId, amount);
        }
    }

    /**
     * Withdraw an amount into an account
     */
    void withdrawFromCustomerAccount() {
        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());

        int accountId = scanAccount();
        double amount = scanAmount();

        if (accessingCustomer.getAccountIds().contains(accountId)) {
            bank.getAccountManager().withdraw(accountId, amount);
        }
    }

    /**
     * Checks if the customer has at least one account.
     *
     * @return {@code true} if the customer has accounts, {@code false} otherwise
     */
    boolean hasAccounts() {
        return accessingCustomer.getAccountIds().size() > 0;
    }

    /**
     * Shows all the customer accounts
     *
     * @return the customer accounts
     */
    String buildAccountList() {
        StringBuilder builder = new StringBuilder();

        for (Integer id : accessingCustomer.getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    /**
     * Prompts the user for an account number
     *
     * @return the account id
     */
    int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(accessingCustomer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    /**
     * Prompts the user for a transaction amount
     *
     * @return the amount to be deposited/withdraw/transferred
     */
    double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}

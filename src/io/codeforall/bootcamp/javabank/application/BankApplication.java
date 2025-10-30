package io.codeforall.bootcamp.javabank.application;

import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

/**
 * The bank application
 */
public class BankApplication {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Operations operations;

    private Bank bank;
    private Customer accessingCustomer;

    /**
     * Creates a new instance of {@code BankApplication} initialized with the given {@link Bank}.
     *
     * @param bank the bank instance to use
     */
    public BankApplication(Bank bank) {
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    /**
     * Starts the bank application
     */
    public void start() {

        mainMenu = buildMainMenu();

        accessingCustomer = bank.getCustomer(scanCustomerId());

        operations = new Operations(accessingCustomer, bank, prompt);
        menuLoop();
    }

    /**
     * Displays the main menu and handles user input by executing selected operations.
     * This method runs recursively until the user chooses to quit.
     */
    private void menuLoop() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        switch (userChoice) {
            case 1:
                operations.showCustomerBalance();
                break;
            case 2:
                operations.depositToCustomerAccount();
                break;
            case 3:
                operations.withdrawFromCustomerAccount();
                break;
            case 4:
                operations.openAccount();
                break;
        }

        menuLoop();
    }

    /**
     * Builds and returns the main menu with user options and validation messages.
     *
     * @return the configured main menu scanner
     */
    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

    /**
     * Prompts the user to select a customer by ID.
     *
     * @return the selected customer ID
     */
    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }



}

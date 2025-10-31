import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static void main(String[] args) {

       Bank bank = new Bank(new AccountManager());

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();


        Prompt prompt = new Prompt(System.in, System.out);

        String[] menuOptions = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};

        IntegerInputScanner customers = new IntegerInputScanner();



        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage("Welcome to Java Bank");

        int answerIndex = prompt.getUserInput(scanner);
        scanner.setMessage("Amount: " + bank.getBalance());
        scanner.setError("Please select a valid option");

//        for (int i = 0; i< menuOptions.length; i++ ){
//            if(menuOptions[i] == "View Balance"){
//                System.out.println("\nAmount: " + bank.getBalance());
//
//            } else if (menuOptions[i] == "Make Deposit"){
//                System.out.println("\nEnter the amount to deposit: ");
//                    DoubleInputScanner depositAmount = new DoubleInputScanner();
//                        Double amount = prompt.getUserInput(depositAmount);
//            } else if (menuOptions[i] == "Make Withdrawals") {
//                System.out.println("\nAmount to withdrawal: ");
//                    DoubleInputScanner withdrawalAmount = new DoubleInputScanner();
//                        double amount = prompt.getUserInput(withdrawalAmount);
//
//            }
//        }

    }

}

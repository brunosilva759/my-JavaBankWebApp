import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.domain.Customer;
import io.codeforall.bootcamp.javabank.managers.AccountManager;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class App {


    public static void main(String[] args) {

        Bank bank = new Bank(new AccountManager());

        Customer customer1 = new Customer(1, "Bruno");
        Customer customer2 = new Customer(2, "Andrew");
        Customer customer3 = new Customer(3, "Kiko");


        Prompt prompt = new Prompt(System.in, System.out);

        String[] menuOptions = {"View Balance", "Make Deposit", "Make Withdrawal", "Open Account", "Quit"};

        //integer set input scanner
        IntegerInputScanner customer = new IntegerInputScanner();
        customer.setMessage("Enter an account ID: ");
        int verification =prompt.getUserInput(customer);

        boolean accountExists = false;

        for(Customer c : bank.getCustomers()) {
            if(c.getId() != verification){
                accountExists = true;
            } else {
                System.out.println("Account ID not valid");
            }

        }


        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage("Welcome to Java Bank");

        int answerIndex = prompt.getUserInput(scanner);
        scanner.setError("Please select a valid option");


        switch (answerIndex) {
            case 1:
                System.out.println("Amount: " + bank.getBalance());
                break;

            case 2:
                System.out.println("Please enter the amount to deposit: ");
                DoubleInputScanner amountToDeposit = new DoubleInputScanner();
                double deposit = prompt.getUserInput(amountToDeposit);
                break;

            case 3:
                System.out.println("Please enter an amount to withdraw: ");
                DoubleInputScanner amountToWithdraw = new DoubleInputScanner();
                double withdraw = prompt.getUserInput(amountToWithdraw);
                break;

            case 4:
                System.out.println("Please enter your name: ");
                StringInputScanner openAccount = new StringInputScanner();
                String accountName = prompt.getUserInput(openAccount);
                break;

            case 5:
                System.out.println();
                break;

            default:
                System.out.println("Please select a valid option");
                break;
        }


    }

}

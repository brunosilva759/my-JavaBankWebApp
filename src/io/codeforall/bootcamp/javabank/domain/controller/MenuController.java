package io.codeforall.bootcamp.javabank.domain.controller;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.UserOptions;
import io.codeforall.bootcamp.javabank.domain.controller.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.domain.controller.operations.Operation;
import io.codeforall.bootcamp.javabank.domain.controller.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.domain.controller.operations.transaction.WithdrawOperation;
import io.codeforall.bootcamp.javabank.domain.model.Bank;
import io.codeforall.bootcamp.javabank.domain.model.Customer;
import io.codeforall.bootcamp.javabank.domain.view.MenuView;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.util.HashMap;
import java.util.Map;

public class MenuController {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationMap;

    public MenuController(Prompt prompt, Customer customer){
        this.prompt = prompt;
        this.operationMap = buildOperationsMap(customer);
    }


    private void menuLoop() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();
    }



    private Map<Integer, Operation> buildOperationsMap(Customer customer) {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceController());
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation());
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation());
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation());

        return map;
    }

}

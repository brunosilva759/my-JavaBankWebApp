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

    private MenuView view;

    public MenuController(Prompt prompt) {
        this.view = new MenuView(prompt);
    }

public void run() {

    int choice;
    while (choice != UserOptions.QUIT.getOption()){
        choice = view.buildMainMenu();
    }

}




}

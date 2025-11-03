package io.codeforall.bootcamp.javabank.domain.view;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.Messages;
import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.UserOptions;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MenuView {

private Prompt prompt;

    public MenuView(Prompt prompt){
        this.prompt = prompt;
    }

    public MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }




}

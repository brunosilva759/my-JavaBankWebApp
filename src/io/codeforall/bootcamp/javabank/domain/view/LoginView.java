package io.codeforall.bootcamp.javabank.domain.view;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.Messages;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

import java.util.Set;

public class LoginView {

    private Prompt prompt;

    public LoginView(Prompt prompt){
        this.prompt = prompt;
    }

    public int scanCustomerId(Set<Integer> ids) {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(ids);
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }



}

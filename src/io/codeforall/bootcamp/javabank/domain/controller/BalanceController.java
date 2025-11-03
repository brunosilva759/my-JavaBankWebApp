package io.codeforall.bootcamp.javabank.domain.controller;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.BankApplication;
import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.UserOptions;
import io.codeforall.bootcamp.javabank.domain.controller.operations.Operation;
import io.codeforall.bootcamp.javabank.domain.view.AbstractBankOperation;
import io.codeforall.bootcamp.javabank.domain.view.BalanceView;

/**
 * A bank operation to check the balance
 *
 * @see AbstractBankOperation
 * @see UserOptions#GET_BALANCE
 */
public class BalanceController extends AbstractBankOperation {

    BalanceView view = new BalanceView();

    /**
     * Creates a new {@code BalanceOperation}
     *
     * @see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public BalanceController(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Executes this bank operation, printing the balance for the customer accounts
     *
     * @see Operation#execute()
     */
    @Override
    public void execute() {
        view.showBalance(customer);
    }
}
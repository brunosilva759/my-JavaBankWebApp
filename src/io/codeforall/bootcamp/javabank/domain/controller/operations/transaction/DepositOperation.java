package io.codeforall.bootcamp.javabank.domain.controller.operations.transaction;

import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.BankApplication;
import io.codeforall.bootcamp.javabank.domain.ToBreakDownIntoPieces.application.UserOptions;
import io.codeforall.bootcamp.javabank.domain.view.AbstractAccountTransactionOperation;

/**
 * An account transaction used to deposit an amount
 * @see AbstractAccountTransactionOperation
 * @see UserOptions#DEPOSIT
 */
public class DepositOperation extends AbstractAccountTransactionOperation {

    /**
     * Initializes a new {@code DepositOperation}
     *
     * @see AbstractAccountTransactionOperation
     */
    public DepositOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    /**
     * Deposit an amount into an account
     *
     * @see AbstractAccountTransactionOperation#execute()
     */
    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.deposit(accountId, amount);
        }
    }
}

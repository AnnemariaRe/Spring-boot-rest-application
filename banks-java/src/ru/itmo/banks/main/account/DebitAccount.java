package ru.itmo.banks.main.account;

import ru.itmo.banks.main.client.Client;
import ru.itmo.banks.main.exception.NullOrNegativeBanksException;

public class DebitAccount extends Account {
    protected DebitAccount(double balance, Client owner, double interestOnBalance) throws Exception {
        super(balance, owner);

        if (interestOnBalance == 0) throw new NullOrNegativeBanksException("Interest on balance cannot be null or negative");
        setAccountType("debit");
        setInterestOnBalance(interestOnBalance);
    }

    @Override
    public boolean isWithdrawAvaliable(double value) {
        return getBalance() < value;
    }

    @Override
    public String toString() {

        return "Account type: Debit account" + System.lineSeparator() +
                String.format("Balance: %1$s", getBalance()) + System.lineSeparator() +
                String.format("Interest on Balance: %1$s", getInterestOnBalance()) + System.lineSeparator();
    }
}

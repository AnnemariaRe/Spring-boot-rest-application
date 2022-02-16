package main.Accounts;

import main.Clients.Client;

public class DebitAccount extends Account {
    protected DebitAccount(double balance, Client owner, double interestOnBalance) throws Exception {
        super(balance, owner);

        if (interestOnBalance == 0) throw new Exception("Interest on balance cannot be null or negative");
        setAccountType("debit");
        setInterestOnBalance(interestOnBalance);
    }

    @Override
    public boolean isWithdrawAvaliable(double value) {
        return !(getBalance() >= value);
    }

    @Override
    public String toString() {

        return "Account type: Debit account" + System.lineSeparator() +
                String.format("Balance: %1$s", getBalance()) + System.lineSeparator() +
                String.format("Interest on Balance: %1$s", getInterestOnBalance()) + System.lineSeparator();
    }
}

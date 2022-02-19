package ru.itmo.banks.account;

import ru.itmo.banks.client.Client;
import ru.itmo.banks.exception.NullOrNegativeBanksException;

public class CreditAccount extends Account {

    public CreditAccount(double balance, Client owner, int limit, double commission)  {
        super(balance, owner);

        if (limit <= 0) throw new NullOrNegativeBanksException("Limit cannot be null or negative");
        if (commission <= 0) throw new NullOrNegativeBanksException("Commission cannot be null or negative");

        setAccountType("credit");
        setLimit(limit);
        setCommission(commission);
    }

    @Override
    public boolean isWithdrawAvaliable(double value) {
        return !(Math.abs(getBalance() - value) < getLimit());
    }

    private double calculateCommission(double value) {
        return value * getCommission() / 100;
    }

    @Override
    public String toString() {

        return "Account type: Credit account" + System.lineSeparator() +
                String.format("Balance: %1$s", getBalance()) + System.lineSeparator() +
                String.format("Credit limit: %1$s", getLimit()) + System.lineSeparator() +
                String.format("Commission: %1$s", getBalance()) + System.lineSeparator();
    }
}

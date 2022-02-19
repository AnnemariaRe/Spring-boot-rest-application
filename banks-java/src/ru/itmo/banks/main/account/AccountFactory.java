package ru.itmo.banks.main.account;

import ru.itmo.banks.main.client.Client;
import ru.itmo.banks.main.exception.NullOrNegativeBanksException;

import java.time.LocalDate;
import java.util.HashMap;

public class AccountFactory {
    
    private double interestOnBalance;
    private int limit;
    private double commission;
    private LocalDate expirationDate;
    private HashMap<Integer, Double> interests;

    public final double getInterestOnBalance() { return interestOnBalance; }
    private void setInterestOnBalance(double value) { interestOnBalance = value; }

    public final int getLimit() { return limit; }
    private void setLimit(int value) { limit = value; }

    public final double getCommission() { return commission; }
    private void setCommission(double value) { commission = value; }

    public final LocalDate getExpirationDate() { return expirationDate; }
    private void setExpirationDate() { expirationDate = LocalDate.MIN; }

    public final HashMap<Integer, Double> getInterests() { return interests; }
    private void setInterests(HashMap<Integer, Double> value) { interests = value; }

    public final void setValues(int limit, double commission, double interestOnBalance, HashMap<Integer, Double> interests) throws Exception {
        if (limit <= 0) throw new NullOrNegativeBanksException("Limit cannot be null or negative");
        setLimit(limit);

        if (commission <= 0 || commission > 100)
            throw new NullOrNegativeBanksException("Commission cannot be null, negative or more than 100");
        setCommission(commission);

        if (interestOnBalance <= 0 || interestOnBalance > 100)
            throw new NullOrNegativeBanksException("Interest on balance cannot be null, negative or more than 100");
        setInterestOnBalance(interestOnBalance);

        if (interests.isEmpty()) throw new NullOrNegativeBanksException("Interests cannot be null");
        setInterests(interests);

        setExpirationDate();
    }

    public final Account createAccount(AccountType account, Client owner, double balance) throws Exception {
        return switch (account) {
            case DEBIT -> new DebitAccount(balance, owner, getInterestOnBalance());
            case DEPOSIT -> new DepositAccount(balance, owner, LocalDate.MAX, getInterests());
            case CREDIT -> new CreditAccount(balance, owner, getLimit(), getCommission());
        };
    }

    public final Account createAccount(AccountType account, Client owner, double balance, LocalDate expirationDate) throws Exception {
        return switch (account) {
            case DEBIT -> new DebitAccount(balance, owner, getInterestOnBalance());
            case DEPOSIT -> new DepositAccount(balance, owner, expirationDate, getInterests());
            case CREDIT -> new CreditAccount(balance, owner, getLimit(), getCommission());
        };
    }

    public final void setCreditLimit(int limit) throws Exception {
        if (limit <= 0) throw new NullOrNegativeBanksException("Limit cannot be null or negative");
        setLimit(limit);
    }

    public final void setCreditComission(double commission) throws Exception {
        if (commission <= 0) throw new NullOrNegativeBanksException("Commission cannot be null or negative");
        setCommission(commission);
    }

    public final void setDebitInterest(double interest) throws Exception {
        if (interest <= 0)
            throw new NullOrNegativeBanksException("Interest cannot be null or negative");
        setInterestOnBalance(interest);
    }

    public final void setDepositInterests(HashMap<Integer, Double> interests) throws Exception {
        if (interests.isEmpty()) throw new NullOrNegativeBanksException("Interests cannot be null");
        setInterests(interests);
    }
}

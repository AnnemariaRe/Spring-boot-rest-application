package main.Accounts;

import main.Clients.Client;

import java.time.LocalDate;
import java.util.HashMap;

public class AccountFactory {
    
    private double InterestOnBalance;
    public final double getInterestOnBalance() { return InterestOnBalance; }
    private void setInterestOnBalance(double value) { InterestOnBalance = value; }

    private int Limit;
    public final int getLimit() { return Limit; }
    private void setLimit(int value) { Limit = value; }

    private double Commission;
    public final double getCommission() { return Commission; }
    private void setCommission(double value) { Commission = value; }

    private LocalDate ExpirationDate;
    public final LocalDate getExpirationDate() { return ExpirationDate; }
    private void setExpirationDate() { ExpirationDate = LocalDate.MIN; }

    private HashMap<Integer, Double> Interests;
    public final HashMap<Integer, Double> getInterests() { return Interests; }
    private void setInterests(HashMap<Integer, Double> value) { Interests = value; }

    public final void setValues(int limit, double commission, double interestOnBalance, HashMap<Integer, Double> interests) throws Exception {
        if (limit <= 0) throw new Exception("Limit cannot be null or negative");
        setLimit(limit);

        if (commission <= 0 || commission > 100)
            throw new Exception("Commission cannot be null, negative or more than 100");
        setCommission(commission);

        if (interestOnBalance <= 0 || interestOnBalance > 100)
            throw new Exception("Interest on balance cannot be null, negative or more than 100");
        setInterestOnBalance(interestOnBalance);

        if (interests.isEmpty()) throw new Exception("Interests cannot be null");
        setInterests(interests);

        setExpirationDate();
    }

    public final Account createAccount(String account, Client owner, double balance) throws Exception {
        return switch (account) {
            case "debit" -> new DebitAccount(balance, owner, getInterestOnBalance());
            case "deposit" -> new DepositAccount(balance, owner, LocalDate.MAX, getInterests());
            case "credit" -> new CreditAccount(balance, owner, getLimit(), getCommission());
            default -> throw new Exception("Invalid account type");
        };
    }

    public final Account createAccount(String account, Client owner, double balance, LocalDate expirationDate) throws Exception {
        return switch (account) {
            case "debit" -> new DebitAccount(balance, owner, getInterestOnBalance());
            case "deposit" -> new DepositAccount(balance, owner, expirationDate, getInterests());
            case "credit" -> new CreditAccount(balance, owner, getLimit(), getCommission());
            default -> throw new Exception("Invalid account type");
        };
    }

    public final void setCreditLimit(int limit) throws Exception {
        if (limit <= 0) throw new Exception("Limit cannot be null or negative");
        setLimit(limit);
    }

    public final void setCreditComission(double commission) throws Exception {
        if (commission <= 0) throw new Exception("Commission cannot be null or negative");
        setCommission(commission);
    }

    public final void setDebitInterest(double interest) throws Exception {
        if (interest <= 0)
            throw new Exception("Interest cannot be null or negative");
        setInterestOnBalance(interest);
    }

    public final void setDepositInterests(HashMap<Integer, Double> interests) throws Exception {
        if (interests.isEmpty()) throw new Exception("Interests cannot be null");
        setInterests(interests);
    }
}

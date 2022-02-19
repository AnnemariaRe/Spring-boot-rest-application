package ru.itmo.banks.main.account;

import ru.itmo.banks.main.client.Client;
import ru.itmo.banks.main.exception.NullOrNegativeBanksException;
import ru.itmo.banks.main.snapshot.CareTaker;
import ru.itmo.banks.main.snapshot.Snapshot;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public abstract class Account {

    private static final int TotalDaysInMonth = 30;

    protected Account(double balance, Client owner) throws Exception {
        if (balance < 0) throw new NullOrNegativeBanksException("Balance cannot be negative");

        setAccountType(null);
        setBalance(balance);
        id = UUID.randomUUID();

        if (owner == null) throw new NullOrNegativeBanksException("Client class cannot be null");
        this.owner = owner;

        setLastInterest(LocalDate.now());
        setProfit(0);
        setInterestOnBalance(0);
        careTaker = new CareTaker(this);
    }

    private double balance;
    private String accountType;
    private final UUID id;
    private final Client owner;
    private double profit;
    private double interestOnBalance;
    private LocalDate firstInterest;
    private LocalDate lastInterest;
    private int limit;
    private double commission;
    private LocalDate expirationDate;
    private final CareTaker careTaker;

    public final double getBalance() { return balance; }
    public final void setBalance(double value) { balance = value; }

    public final String getAccountType() { return accountType; }
    public final void setAccountType(String value) { accountType = value; }

    public final UUID getId() { return id; }

    public final Client getOwner() { return owner; }

    public final double getProfit() { return profit; }
    protected final void setProfit(double value) { profit = value; }

    public final double getInterestOnBalance() { return interestOnBalance; }
    protected final void setInterestOnBalance(double value) { interestOnBalance = value; }

    public final LocalDate getFirstInterest() { return firstInterest; }
    protected final void setFirstInterest(LocalDate value) { firstInterest = value;}

    public final LocalDate getLastInterest() { return lastInterest; }
    protected final void setLastInterest(LocalDate value) { lastInterest = value; }

    public final int getLimit() { return limit; }
    protected final void setLimit(int value) { limit = value; }

    public final double getCommission() { return commission; }
    protected final void setCommission(double value) { commission = value; }

    public final LocalDate getExpirationDate() { return expirationDate; }
    protected final void setExpirationDate(LocalDate value) { expirationDate = value; }

    public final CareTaker getCareTaker() { return careTaker; }

    public void calculateDayProfit() throws Exception {
        if (getInterestOnBalance() <= 0) throw new NullOrNegativeBanksException("Cannot calculate day profit");

        if (getProfit() == 0) setFirstInterest(LocalDate.now());

        if (getLastInterest().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            throw new NullOrNegativeBanksException("The profit has already counted today");
        }

        setProfit(getBalance() * getInterestOnBalance() / 365 / 100);
        setLastInterest(LocalDate.now());
    }

    public void updateBalance() {
        Period duration = Period.between(getFirstInterest(), getLastInterest());
        if (duration.getDays() == TotalDaysInMonth) {
            setBalance(getBalance() + getProfit());
            setProfit(0);
        }
    }

    public void restore(Snapshot snapshot)
    {
        setBalance(snapshot.getState());
    }

    public abstract boolean isWithdrawAvaliable(double value);
}

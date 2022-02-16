package main.Accounts;

import main.Clients.Client;
import main.Snapshot.CareTaker;
import main.Snapshot.Snapshot;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public abstract class Account {

    private static final int TotalDaysInMonth = 30;

    protected Account(double balance, Client owner) throws Exception {
        if (balance < 0) throw new Exception("Balance cannot be negative");

        setAccountType(null);
        setBalance(balance);
        Id = UUID.randomUUID();

        if (owner == null) throw new ClassNotFoundException("Client class cannot be null");
        Owner = owner;

        setLastInterest(LocalDate.now());
        setProfit(0);
        setInterestOnBalance(0);
        CareTaker = new CareTaker(this);
    }

    private double Balance;
    public final double getBalance() { return Balance; }
    public final void setBalance(double value) { Balance = value; }

    private String AccountType;
    public final String getAccountType() { return AccountType; }
    public final void setAccountType(String value) { AccountType = value; }

    private final UUID Id;
    public final UUID getId() { return Id; }

    private final Client Owner;
    public final Client getOwner() { return Owner; }

    private double Profit;
    public final double getProfit() { return Profit; }
    protected final void setProfit(double value) { Profit = value; }

    private double InterestOnBalance;
    public final double getInterestOnBalance() { return InterestOnBalance; }
    protected final void setInterestOnBalance(double value) { InterestOnBalance = value; }

    private LocalDate FirstInterest;
    public final LocalDate getFirstInterest() { return FirstInterest; }
    protected final void setFirstInterest(LocalDate value) { FirstInterest = value;}

    private LocalDate LastInterest;
    public final LocalDate getLastInterest() { return LastInterest; }
    protected final void setLastInterest(LocalDate value) { LastInterest = value; }

    private int Limit;
    public final int getLimit() { return Limit; }
    protected final void setLimit(int value) { Limit = value; }

    private double Commission;
    public final double getCommission() { return Commission; }
    protected final void setCommission(double value) { Commission = value; }

    private LocalDate ExpirationDate;
    public final LocalDate getExpirationDate() { return ExpirationDate; }
    protected final void setExpirationDate(LocalDate value) { ExpirationDate = value; }

    private final CareTaker CareTaker;
    public final CareTaker getCareTaker() { return CareTaker; }

    public void calculateDayProfit() throws Exception {
        if (getInterestOnBalance() <= 0) throw new Exception("Cannot calculate day profit");

        if (getProfit() == 0) setFirstInterest(LocalDate.now());

        if (getLastInterest().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            throw new Exception("The profit has already counted today");
        }

        setProfit(getBalance() * getInterestOnBalance() / 365 / 100);
        setLastInterest(LocalDate.now());
    }

    public void updateBalance()
    {
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

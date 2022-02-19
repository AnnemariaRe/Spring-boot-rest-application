package ru.itmo.banks.main.account;

import ru.itmo.banks.main.client.Client;
import ru.itmo.banks.main.exception.NullOrNegativeBanksException;

import java.time.LocalDate;
import java.util.HashMap;

public class DepositAccount extends Account {

    protected DepositAccount(double balance, Client owner, LocalDate expirationDate, HashMap<Integer, Double> interests) throws Exception {
        super(balance, owner);
        
        if (interests.isEmpty()) throw new NullOrNegativeBanksException("List of interests cannot be null");
        
        setAccountType("deposit");
        setExpirationDate(expirationDate);
        setInterestOnBalance(interests);
    }
    
    protected void setInterestOnBalance(HashMap<Integer, Double> interests) {
        for (var interest : interests.entrySet()) {
            if (getBalance() < interest.getKey()) {
                setInterestOnBalance(interest.getValue());
                break;
            }
        }

        if (getBalance() == 0) setInterestOnBalance(0);
    }
    
    @Override
    public boolean isWithdrawAvaliable(double value) {
        return !(getBalance() >= value) || !getExpirationDate().isBefore(LocalDate.now());
    }

    @Override
    public String toString()
    {

        return "Account type: Deposit account" + System.lineSeparator() +
                String.format("Balance: %1$s", getBalance()) + System.lineSeparator() +
                String.format("Expiration date: %1$s", getExpirationDate()) + System.lineSeparator() +
                String.format("Interest on Balance: %1$s", getInterestOnBalance()) + System.lineSeparator();
    }
}

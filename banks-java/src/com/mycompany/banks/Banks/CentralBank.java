package com.mycompany.banks.Banks;

import com.mycompany.banks.Clients.Client;
import com.mycompany.banks.Transactions.Transaction;
import com.mycompany.banks.Accounts.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CentralBank {

    public CentralBank()
    {
        setBanks(new ArrayList<>());
    }

    private List<Bank> Banks;
    public final List<Bank> getBanks()
    {
        return Banks;
    }
    public final void setBanks(List<Bank> value)
    {
        Banks = value;
    }

    public final Bank createBank(String name, double debitInterest, HashMap<Integer, Double> depositInterests, double creditComission, int creditLimit) throws Exception {
        var newBank = new Bank(name, debitInterest, depositInterests, creditComission, creditLimit);
        getBanks().add(newBank);
        return newBank;
    }

    public final Client addClientToBank(Client client, UUID bankId) throws Exception {
        var bank = getBanks()
                .stream()
                .filter(b -> b.getId() == bankId)
                .findFirst()
                .orElse(null);
        return bank != null ? bank.addNewClient(client) : null;
    }

    public final Account createAccount(Client client, UUID bankId, String accountType, double money) throws Exception {
        var bank = getBanks().stream().filter(b -> b.getId() == bankId).findFirst().orElse(null);
        return switch (accountType)
                {
                    case "debit" -> {
                        assert bank != null;
                        yield bank.createDebitAccount(client, money);
                    }
                    case "deposit" -> {
                        assert bank != null;
                        yield bank.createDepositAccount(client, money);
                    }
                    case "credit" -> {
                        assert bank != null;
                        yield bank.createCreditAccount(client, money);
                    }
                    default -> null;
                };
    }

    public final void transferMoneyBetweenBanks(Account accountFrom, Account accountTo, double money) throws Exception {
        if (accountFrom == null || accountTo == null)
        {
            throw new Exception("Accounts cannot be null");
        }
        if (getBanks().stream().noneMatch(bank -> bank.getAccounts().contains(accountFrom)))
        {
            throw new Exception("Account is not find");
        }
        if (getBanks().stream().noneMatch(bank -> bank.getAccounts().contains(accountTo)))
        {
            throw new Exception("Account is not find");
        }
        var transaction = new Transaction(accountFrom);
        transaction.transfer(accountTo, money);
    }

    public final void updateInterestOnBalance() throws Exception {
        for (var account : getBanks().stream().flatMap(bank -> bank.getAccounts().stream()).toList()) {
                account.calculateDayProfit();
                account.updateBalance();
        }
    }

}

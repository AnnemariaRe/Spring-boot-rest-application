package ru.itmo.banks.bank;

import ru.itmo.banks.client.Client;
import ru.itmo.banks.exception.NullOrNegativeBanksException;
import ru.itmo.banks.transaction.Transaction;
import ru.itmo.banks.account.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CentralBank {

    public CentralBank()
    {
        setBanks(new ArrayList<>());
    }

    private List<Bank> banks;
    public final List<Bank> getBanks()
    {
        return banks;
    }
    public final void setBanks(List<Bank> value)
    {
        banks = value;
    }

    public final Bank createBank(String name, double debitInterest, HashMap<Integer, Double> depositInterests, double creditComission, int creditLimit) {
        var newBank = new Bank(name, debitInterest, depositInterests, creditComission, creditLimit);
        getBanks().add(newBank);
        return newBank;
    }

    public final Client addClientToBank(Client client, UUID bankId)  {
        var bank = getBanks()
                .stream()
                .filter(b -> b.getId() == bankId)
                .findFirst()
                .orElse(null);
        return bank != null ? bank.addNewClient(client) : null;
    }

    public final Account createAccount(Client client, UUID bankId, String accountType, double money)  {
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

    public final void transferMoneyBetweenBanks(Account accountFrom, Account accountTo, double money)  {
        if (accountFrom == null || accountTo == null)
        {
            throw new NullOrNegativeBanksException("Accounts cannot be null");
        }
        if (getBanks().stream().noneMatch(bank -> bank.getAccounts().contains(accountFrom)))
        {
            throw new NullOrNegativeBanksException("Account is not find");
        }
        if (getBanks().stream().noneMatch(bank -> bank.getAccounts().contains(accountTo)))
        {
            throw new NullOrNegativeBanksException("Account is not find");
        }
        var transaction = new Transaction(accountFrom);
        transaction.transfer(accountTo, money);
    }

    public final void updateInterestOnBalance()  {
        for (var account : getBanks().stream().flatMap(bank -> bank.getAccounts().stream()).toList()) {
                account.calculateDayProfit();
                account.updateBalance();
        }
    }

}

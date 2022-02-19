package ru.itmo.banks.bank;

import ru.itmo.banks.account.AccountType;
import ru.itmo.banks.client.Client;
import ru.itmo.banks.client.ClientBuilder;
import ru.itmo.banks.exception.BanksException;
import ru.itmo.banks.exception.NullOrNegativeBanksException;
import ru.itmo.banks.observer.EventManager;
import ru.itmo.banks.transaction.Transaction;
import ru.itmo.banks.account.Account;
import ru.itmo.banks.account.AccountFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final AccountFactory accountFactory;

    public Bank(String name, double debitInterest, HashMap<Integer, Double> depositInterests, double creditCommission, int creditLimit)  {
        id = UUID.randomUUID();
        if (name.isEmpty()) throw new NullOrNegativeBanksException("Name cannot be null");

        setName(name);
        accountFactory = new AccountFactory();
        accountFactory.setValues(creditLimit, creditCommission, debitInterest, depositInterests);
        accounts = new ArrayList<>();
        clients = new HashMap<>();
        events = new EventManager("debit", "deposit", "credit");
    }

    private final UUID id;
    private String name;
    private final List<Account> accounts;
    private final HashMap<Client, List<Account>> clients;
    private final EventManager events;

    public final UUID getId() { return id; }

    public final String getName() { return name; }
    public final void setName(String name) { this.name = name; }

    public final List<Account> getAccounts() { return accounts; }

    public final HashMap<Client, List<Account>> getClients() { return clients; }

    public final Client addNewClient(Client client)  {
        if (client == null) throw new NullOrNegativeBanksException("Client cannot be null");

        if (getClients().containsKey(client)) throw new NullOrNegativeBanksException("Bank already has current client");

        getClients().put(client, new ArrayList<>());
        return client;
    }

    public final Client addNewClient(String name, String surname)  {
        if (name.isEmpty() || surname.isEmpty()) throw new NullOrNegativeBanksException("Client cannot be null");

        Client newClient = new ClientBuilder().setNameAndSurname(name, surname).build();
        if (getClients().containsKey(newClient)) throw new BanksException("Bank already has current client");
        getClients().put(newClient, new ArrayList<>());

        return newClient;
    }

    public final Client addNewClient(String name, String surname, String address, int passportId)  {
        if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || passportId <= 0) throw new BanksException("Client cannot be null");

        Client newClient = new ClientBuilder()
                .setNameAndSurname(name, surname)
                .setAddress(address)
                .setPassportId(passportId)
                .build();
        if (getClients().containsKey(newClient)) throw new BanksException("Bank already has current client");
        getClients().put(newClient, new ArrayList<>());

        return newClient;
    }

    public final void removeClient(Client client) {
        if (getClients() != null) getClients().remove(client);
    }

    public final Account createDebitAccount(Client client, double money)  {
        if (!getClients().containsKey(client)) throw new BanksException("Cannot find current client");

        var account = accountFactory.createAccount(AccountType.DEBIT, client, money);
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final Account createDepositAccount(Client client, double money)  {
        if (!getClients().containsKey(client)) throw new BanksException("Cannot find current client");

        var account = accountFactory.createAccount(AccountType.DEPOSIT, client, money, accountFactory.getExpirationDate());
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final Account createCreditAccount(Client client, double money)  {
        if (!getClients().containsKey(client)) throw new BanksException("Cannot find current client");

        var account = accountFactory.createAccount(AccountType.CREDIT, client, money);
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final void subscribeUser(Client client)  {
        if (!getClients().containsKey(client)) throw new BanksException("Cannot subscribe client");
        var accounts = getClients().get(client);

        for (var acc : accounts) events.subscribe(acc.getAccountType(), client);
    }

    public final void unsubscribeUser(Client client)  {
        if (!getClients().containsKey(client)) throw new BanksException("Cannot subscribe client");
        var accounts = getClients().get(client);

        for (var acc : accounts) events.unsubscribe(acc.getAccountType(), client);
    }

    public final void replenishMoney(UUID accountId, double money)  {
        var account = getAccounts().stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
        var transaction = new Transaction(account);
        transaction.replenish(money);
    }

    public final void withdrawMoney(UUID accountId, double money)  {
        var account = getAccounts().stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
        var transaction = new Transaction(account);
        transaction.withdraw(money);
    }

    public final void transferMoney(UUID withdrawAccountId, UUID replenishAccountId, double money)  {
        var accountFrom = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == withdrawAccountId)
                .findFirst()
                .orElse(null);
        var accountTo = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == replenishAccountId)
                .findFirst()
                .orElse(null);
        var transaction = new Transaction(accountFrom);
        transaction.transfer(accountTo, money);
    }

    public final void undoTransaction(UUID accountId)  {
        var account = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElse(null);
        var transaction = new Transaction(account);
        transaction.undoTransaction();
    }

    public final void UpdateDebitInterest(double interest)  {
        accountFactory.setDebitInterest(interest);
        events.notify("debit");
    }

    public final void UpdateDepositInterests(HashMap<Integer, Double> interests)  {
        accountFactory.setDepositInterests(interests);
        events.notify("deposit");
    }

    public final void UpdateCreditLimit(int limit)  {
        accountFactory.setCreditLimit(limit);
        events.notify("credit");
    }

    public final void UpdateCreditComission(double commission)  {
        accountFactory.setCreditComission(commission);
        events.notify("credit");
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(String.format("BANK - %1$s", getName())).append(System.lineSeparator());
        sb.append("List of clients: ").append(System.lineSeparator());

        for (var client : getClients().entrySet()) {
            sb.append(client.getKey().getName()).append(' ').append(client.getKey().getSurname()).append(System.lineSeparator());
            sb.append("Client accounts: ").append(System.lineSeparator());
            for (var account : client.getValue()) {
                sb.append(account.getAccountType()).append(" account").append(System.lineSeparator());
                sb.append("-------------------").append(System.lineSeparator());
            }
        }

        sb.append("---------------------------------").append(System.lineSeparator());
        return sb.toString();
    }
}

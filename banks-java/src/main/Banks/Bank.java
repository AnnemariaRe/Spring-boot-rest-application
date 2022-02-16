package main.Banks;

import main.Accounts.Account;
import main.Accounts.AccountFactory;
import main.Clients.Client;
import main.Clients.ClientBuilder;
import main.Observer.EventManager;
import main.Transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final AccountFactory accountFactory;

    public Bank(String name, double debitInterest, HashMap<Integer, Double> depositInterests, double creditCommission, int creditLimit) throws Exception {
        Id = UUID.randomUUID();
        if (name.isEmpty()) throw new Exception("Name cannot be null");

        setName(name);
        accountFactory = new AccountFactory();
        accountFactory.setValues(creditLimit, creditCommission, debitInterest, depositInterests);
        Accounts = new ArrayList<>();
        Clients = new HashMap<>();
        events = new EventManager("debit", "deposit", "credit");
    }

    private final UUID Id;
    public final UUID getId() { return Id; }

    private String Name;
    public final String getName() { return Name; }
    public final void setName(String name) { Name = name; }

    private final List<Account> Accounts;
    public final List<Account> getAccounts() { return Accounts; }

    private final HashMap<Client, List<Account>> Clients;
    public final HashMap<Client, List<Account>> getClients() { return Clients; }

    public EventManager events;

    public final Client addNewClient(Client client) throws Exception {
        if (client == null) throw new Exception("Client cannot be null");

        if (getClients().containsKey(client)) throw new Exception("Bank already has current client");

        getClients().put(client, new ArrayList<>());
        return client;
    }

    public final Client addNewClient(String name, String surname) throws Exception {
        if (name.isEmpty() || surname.isEmpty()) throw new Exception("Client cannot be null");

        Client newClient = new ClientBuilder().setNameAndSurname(name, surname).build();
        if (getClients().containsKey(newClient)) throw new Exception("Bank already has current client");
        getClients().put(newClient, new ArrayList<>());

        return newClient;
    }

    public final Client addNewClient(String name, String surname, String address, int passportId) throws Exception {
        if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || passportId <= 0) throw new Exception("Client cannot be null");

        Client newClient = new ClientBuilder().setNameAndSurname(name, surname).setAddress(address).setPassportId(passportId).build();
        if (getClients().containsKey(newClient)) throw new Exception("Bank already has current client");
        getClients().put(newClient, new ArrayList<>());

        return newClient;
    }

    public final void removeClient(Client client) {
        if (getClients() != null) getClients().remove(client);
    }

    public final Account createDebitAccount(Client client, double money) throws Exception {
        if (!getClients().containsKey(client)) throw new Exception("Cannot find current client");

        var account = accountFactory.createAccount("debit", client, money);
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final Account createDepositAccount(Client client, double money) throws Exception {
        if (!getClients().containsKey(client)) throw new Exception("Cannot find current client");

        var account = accountFactory.createAccount("deposit", client, money, accountFactory.getExpirationDate());
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final Account createCreditAccount(Client client, double money) throws Exception {
        if (!getClients().containsKey(client)) throw new Exception("Cannot find current client");

        var account = accountFactory.createAccount("credit", client, money);
        getAccounts().add(account);
        if (getClients().get(client) != null) getClients().get(client).add(account);

        return account;
    }

    public final void subscribeUser(Client client) throws Exception {
        if (!getClients().containsKey(client)) throw new Exception("Cannot subscribe client");
        var accounts = getClients().get(client);

        for (var acc : accounts) events.subscribe(acc.getAccountType(), client);
    }

    public final void unsubscribeUser(Client client) throws Exception {
        if (!getClients().containsKey(client)) throw new Exception("Cannot subscribe client");
        var accounts = getClients().get(client);

        for (var acc : accounts) events.unsubscribe(acc.getAccountType(), client);
    }

    public final void replenishMoney(UUID accountId, double money) throws Exception {
        var account = getAccounts().stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
        var transaction = new Transaction(account);
        transaction.replenish(money);
    }

    public final void withdrawMoney(UUID accountId, double money) throws Exception {
        var account = getAccounts().stream().filter(acc -> acc.getId() == accountId).findFirst().orElse(null);
        var transaction = new Transaction(account);
        transaction.withdraw(money);
    }

    public final void transferMoney(UUID withdrawAccountId, UUID replenishAccountId, double money) throws Exception {
        var account1 = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == withdrawAccountId)
                .findFirst()
                .orElse(null);
        var account2 = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == replenishAccountId)
                .findFirst()
                .orElse(null);
        var transaction = new Transaction(account1);
        transaction.transfer(account2, money);
    }

    public final void undoTransaction(UUID accountId) throws Exception {
        var account = getAccounts()
                .stream()
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElse(null);
        var transaction = new Transaction(account);
        transaction.undoTransaction();
    }

    public final void UpdateDebitInterest(double interest) throws Exception {
        accountFactory.setDebitInterest(interest);
        events.notify("debit");
    }

    public final void UpdateDepositInterests(HashMap<Integer, Double> interests) throws Exception {
        accountFactory.setDepositInterests(interests);
        events.notify("deposit");
    }

    public final void UpdateCreditLimit(int limit) throws Exception {
        accountFactory.setCreditLimit(limit);
        events.notify("credit");
    }

    public final void UpdateCreditComission(double commission) throws Exception {
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

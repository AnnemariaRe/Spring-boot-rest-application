package test;

import main.Banks.Bank;
import main.Banks.CentralBank;
import main.Clients.ClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddClientWithAccountsTest {
    private CentralBank service;
    private Bank bank;

    @Before
    public void setup() throws Exception {
        service = new CentralBank();
        HashMap<Integer, Double> interests = new HashMap<>();

        interests.put(5000, 3.0);
        interests.put(50000, 4.0);
        interests.put(10000, 5.0);

        bank = service.createBank("AlfaBank", 3, interests, 20, 30000);
    }

    @Test
    public void addClientWithDebitAccountTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "debit", 12937);

        assertEquals("debit", account.getAccountType());
        assertEquals(3, account.getInterestOnBalance());
        assertEquals(12937, Objects.requireNonNull(bank.getClients()
                        .get(client)
                        .stream()
                        .filter(acc -> acc.getOwner() == client)
                        .findFirst()
                        .orElse(null))
                .getBalance());
    }

    @Test
    public void AddClientWithDepositAccountTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "deposit", 7345);

        assertEquals("deposit", account.getAccountType());
        assertEquals(4, account.getInterestOnBalance());
        assertEquals(7345, Objects.requireNonNull(bank.getClients()
                        .get(client)
                        .stream()
                        .filter(acc -> acc.getOwner() == client)
                        .findFirst()
                        .orElse(null))
                .getBalance());
    }

    @Test
    public void AddClientWithCreditAccountTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "credit", 1);

        assertEquals("credit", account.getAccountType());
        assertEquals(20, account.getCommission());
        assertEquals(30000, account.getLimit());
        assertEquals(1, Objects.requireNonNull(bank.getClients()
                        .get(client)
                        .stream()
                        .filter(acc -> acc.getOwner() == client)
                        .findFirst()
                        .orElse(null))
                .getBalance());
    }
}


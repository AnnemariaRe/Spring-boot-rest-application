package ru.itmo.banks.test;

import ru.itmo.banks.main.bank.Bank;
import ru.itmo.banks.main.bank.CentralBank;
import ru.itmo.banks.main.client.ClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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

        Assertions.assertEquals("debit", account.getAccountType());
        Assertions.assertEquals(3, account.getInterestOnBalance());
        Assertions.assertEquals(12937, Objects.requireNonNull(bank.getClients()
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

        Assertions.assertEquals("deposit", account.getAccountType());
        Assertions.assertEquals(4, account.getInterestOnBalance());
        Assertions.assertEquals(7345, Objects.requireNonNull(bank.getClients()
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

        Assertions.assertEquals("credit", account.getAccountType());
        Assertions.assertEquals(20, account.getCommission());
        Assertions.assertEquals(30000, account.getLimit());
        Assertions.assertEquals(1, Objects.requireNonNull(bank.getClients()
                        .get(client)
                        .stream()
                        .filter(acc -> acc.getOwner() == client)
                        .findFirst()
                        .orElse(null))
                .getBalance());
    }
}


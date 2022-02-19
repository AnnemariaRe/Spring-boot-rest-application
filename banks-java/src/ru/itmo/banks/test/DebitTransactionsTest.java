package ru.itmo.banks.test;

import ru.itmo.banks.main.bank.Bank;
import ru.itmo.banks.main.bank.CentralBank;
import ru.itmo.banks.main.client.ClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DebitTransactionsTest {
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
    public void replenishTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "debit", 12937);

        bank.replenishMoney(account.getId(), 10000);

        Assertions.assertEquals(22937, account.getBalance());
    }

    @Test
    public void withdrawExceptionTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "debit", 5);

        try {
            bank.withdrawMoney(account.getId(), 30);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void transferTest() throws Exception {
        var client1 = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account1 = service.createAccount(client1, bank.getId(), "debit", 12937);
        var client2 = service.addClientToBank(new ClientBuilder().setNameAndSurname("Ksenia", "Vasutinskaya").build(), bank.getId());
        var account2 =service.createAccount(client2, bank.getId(), "debit", 10);

        bank.transferMoney(account1.getId(), account2.getId(), 10000);

        Assertions.assertEquals(2937, account1.getBalance());
        Assertions.assertEquals(10010, account2.getBalance());
    }

    @Test
    public void undoTest() throws Exception {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "debit", 12937);

        bank.withdrawMoney(account.getId(), 10000);
        bank.undoTransaction(account.getId());

        Assertions.assertEquals(12937, account.getBalance());
    }
}

import org.junit.Before;
import org.junit.Test;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.client.ClientBuilder;

import java.util.HashMap;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class AddClientWithAccountsTest {
    private CentralBank service;
    private Bank bank;

    @Before
    public void setup() {
        service = new CentralBank();
        HashMap<Integer, Double> interests = new HashMap<>();

        interests.put(5000, 3.0);
        interests.put(50000, 4.0);
        interests.put(10000, 5.0);

        bank = service.createBank("AlfaBank", 3, interests, 20, 30000);
    }

    @Test
    public void addClientWithDebitAccountTest() {
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
    public void AddClientWithDepositAccountTest() {
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
    public void AddClientWithCreditAccountTest() {
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


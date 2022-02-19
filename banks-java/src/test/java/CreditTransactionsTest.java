import org.junit.Before;
import org.junit.Test;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.client.ClientBuilder;

import java.util.HashMap;

import static junit.framework.TestCase.*;

public class CreditTransactionsTest {
    private CentralBank service;
    private Bank bank;

    @Before
    public void setup() {
        service = new CentralBank();
        HashMap<Integer, Double> interests = new HashMap<>();

        interests.put(5000, 3.0);
        interests.put(50000, 4.0);
        interests.put(10000, 5.0);

        bank = service.createBank("AlfaBank", 3, interests, 20, 3000);
    }

    @Test
    public void replenishTest() {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "credit", 5000);

        bank.replenishMoney(account.getId(), 10000);

        assertEquals(15000, account.getBalance());
    }

    @Test
    public void withdrawExceptionTest() {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "credit", 5);

        try {
            bank.withdrawMoney(account.getId(), 3010);
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void transferTest() {
        var client1 = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account1 = service.createAccount(client1, bank.getId(), "credit", 200);
        var client2 = service.addClientToBank(new ClientBuilder().setNameAndSurname("Ksenia", "Vasutinskaya").build(), bank.getId());
        var account2 =service.createAccount(client2, bank.getId(), "credit", 10);

        bank.transferMoney(account1.getId(), account2.getId(), 100);

        assertEquals(100, account1.getBalance());
        assertEquals(110, account2.getBalance());
    }

    @Test
    public void undoTest() {
        var client = service.addClientToBank(new ClientBuilder().setNameAndSurname("Annemarija", "Repenko").build(), bank.getId());
        var account = service.createAccount(client, bank.getId(), "credit", 500);

        bank.withdrawMoney(account.getId(), 10);
        bank.undoTransaction(account.getId());

        assertEquals(500, account.getBalance());
    }
}

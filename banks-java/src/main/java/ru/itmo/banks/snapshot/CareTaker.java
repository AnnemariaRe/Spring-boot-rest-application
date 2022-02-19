package ru.itmo.banks.snapshot;

import ru.itmo.banks.account.Account;
import ru.itmo.banks.exception.BanksException;

import java.util.ArrayList;

public class CareTaker {
    private final Account account;
    private final ArrayList<Snapshot> balanceStates = new ArrayList<>();

    public CareTaker(Account account) {
        this.account = account;
    }

    public final ArrayList<Snapshot> getBalanceStates() { return balanceStates; }

    public final void saveState(Snapshot balance) {
        balanceStates.add(balance);
    }

    public final void restore()  {
        var result = getBalanceStates().get(getBalanceStates().size() - 1);
        if (result == null) throw new BanksException("Cannot restore the state. Collection is empty :(");

        account.restore(result);
        balanceStates.remove(result);

    }

}

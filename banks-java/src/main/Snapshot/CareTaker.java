package main.Snapshot;

import main.Accounts.Account;

import java.util.LinkedList;

public class CareTaker {
    private final Account account;
    private final LinkedList<Snapshot> balanceStates = new LinkedList<>();

    public CareTaker(Account account) {
        this.account = account;
    }

    public final LinkedList<Snapshot> getBalanceStates() { return balanceStates; }

    public final void saveState(Snapshot balance) {
        balanceStates.add(balance);
    }

    public final void restore() throws Exception {
        var result = getBalanceStates().getLast();
        if (result == null) throw new Exception("Cannot restore the state. Collection is empty :(");

        account.restore(result);
        balanceStates.remove(result);

    }

}

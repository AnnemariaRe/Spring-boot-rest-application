package ru.itmo.banks.main.transaction;

import ru.itmo.banks.main.account.Account;
import ru.itmo.banks.main.snapshot.Snapshot;

public class ReplenishTransaction extends Transaction {
    public ReplenishTransaction(Account account) { super(account); }

    @Override
    public void replenish(double money) throws Exception {
        if (money <= 0) throw new Exception("Money cannot be null or negative");

        getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
        getAccount().setBalance(getAccount().getBalance() + money);
    }

}

package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;
import ru.itmo.banks.exception.NullOrNegativeBanksException;
import ru.itmo.banks.snapshot.Snapshot;

public class ReplenishTransaction extends Transaction {
    public ReplenishTransaction(Account account) { super(account); }

    @Override
    public void replenish(double money)  {
        if (money <= 0) throw new NullOrNegativeBanksException("Money cannot be null or negative");

        getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
        getAccount().setBalance(getAccount().getBalance() + money);
    }

}

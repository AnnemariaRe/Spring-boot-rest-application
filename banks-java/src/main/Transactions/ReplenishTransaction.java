package main.Transactions;

import main.Accounts.Account;
import main.Snapshot.Snapshot;

public class ReplenishTransaction extends Transaction {
    public ReplenishTransaction(Account account) { super(account); }

    @Override
    public void replenish(double money) throws Exception {
        if (money <= 0) throw new Exception("Money cannot be null or negative");

        getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
        getAccount().setBalance(getAccount().getBalance() + money);
    }

}

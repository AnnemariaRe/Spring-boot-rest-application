package main.Transactions;

import main.Accounts.Account;
import main.Accounts.CreditAccount;
import main.Snapshot.Snapshot;

public class
CreditTransaction extends Transaction {
    public CreditTransaction(Account account) { super(account); }

    @Override
    public void withdraw(double money) throws Exception {
        if (getAccount() instanceof CreditAccount) {
            if (money <= 0) throw new Exception("Money cannot be null or negative");

            if (getAccount().isWithdrawAvaliable(money))
                throw new Exception("Withdrawal is not available");

            getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
            getAccount().setBalance(getAccount().getBalance() - money);
        }
        else if (getTransactionHandler() != null) getTransactionHandler().withdraw(money);
    }

    @Override
    public void transfer(Account withdrawAccount, double money) throws Exception {
        if (getAccount() instanceof CreditAccount) {
            if (money <= 0) throw new Exception("Money cannot be null or negative");

            if (getAccount().isWithdrawAvaliable(money))
                throw new Exception("Withdrawal is not available");

            getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
            withdrawAccount.getCareTaker().saveState(new Snapshot(withdrawAccount.getBalance()));
            getAccount().setBalance(getAccount().getBalance() - money);
            withdrawAccount.setBalance(withdrawAccount.getBalance() + money);
        }
        else if (getTransactionHandler() != null) getTransactionHandler().transfer(withdrawAccount, money);
    }
}

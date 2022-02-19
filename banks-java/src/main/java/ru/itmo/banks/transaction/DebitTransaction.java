package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;
import ru.itmo.banks.account.DebitAccount;
import ru.itmo.banks.exception.BanksException;
import ru.itmo.banks.snapshot.Snapshot;

public class DebitTransaction extends Transaction {
    public DebitTransaction(Account account) { super(account); }

    @Override
    public void withdraw(double money)  {
        if (getAccount() instanceof DebitAccount) {
            if (money <= 0) throw new BanksException("Money cannot be null or negative");

            if (getAccount().isWithdrawAvaliable(money))
                throw new BanksException("Withdrawal is not available");

            getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
            getAccount().setBalance(getAccount().getBalance() - money);
        }
        else if (getTransactionHandler() != null) getTransactionHandler().withdraw(money);
    }

    @Override
    public void transfer(Account withdrawAccount, double money)  {
        if (getAccount() instanceof DebitAccount) {
            if (money <= 0) throw new BanksException("Money cannot be null or negative");

            if (getAccount().isWithdrawAvaliable(money))
                throw new BanksException("Withdrawal is not available");

            getAccount().getCareTaker().saveState(new Snapshot(getAccount().getBalance()));
            withdrawAccount.getCareTaker().saveState(new Snapshot(withdrawAccount.getBalance()));
            getAccount().setBalance(getAccount().getBalance() - money);
            withdrawAccount.setBalance(withdrawAccount.getBalance() + money);
        }
        else if (getTransactionHandler() != null) getTransactionHandler().transfer(withdrawAccount, money);
    }

}

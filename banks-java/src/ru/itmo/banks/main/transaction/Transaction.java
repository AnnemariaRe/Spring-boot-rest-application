package ru.itmo.banks.main.transaction;

import ru.itmo.banks.main.account.Account;

public class Transaction implements ITransactionHandler
{
    public Transaction(Account account)
    {
        this.account = account;
    }

    private ITransactionHandler transactionHandler;
    private final Account account;

    protected final ITransactionHandler getTransactionHandler() { return transactionHandler; }
    private void setTransactionHandler(ITransactionHandler value) { transactionHandler = value; }

    protected final Account getAccount() { return account; }

    public final ITransactionHandler setNext(ITransactionHandler handler) {
        setTransactionHandler(handler);
        return handler;
    }

    public void replenish(double money) throws Exception {
        setTransactionHandler(new ReplenishTransaction(getAccount()));
        getTransactionHandler().replenish(money);
    }

    public void withdraw(double money) throws Exception {
        setTransactionHandler(new DebitTransaction(getAccount()));
        getTransactionHandler().setNext(new DepositTransaction(getAccount())).setNext(new CreditTransaction(getAccount()));
        getTransactionHandler().withdraw(money);
    }

    public void transfer(Account replenishAccount, double money) throws Exception {
        setTransactionHandler(new DebitTransaction(getAccount()));
        getTransactionHandler().setNext(new DepositTransaction(getAccount())).setNext(new CreditTransaction(getAccount()));
        getTransactionHandler().transfer(replenishAccount, money);
    }

    public void undoTransaction() throws Exception {
        getAccount().getCareTaker().restore();
    }
}

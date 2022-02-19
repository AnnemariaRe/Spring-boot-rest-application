package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;

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

    public void replenish(double money)  {
        setTransactionHandler(new ReplenishTransaction(getAccount()));
        getTransactionHandler().replenish(money);
    }

    public void withdraw(double money)  {
        setTransactionHandler(new DebitTransaction(getAccount()));
        getTransactionHandler().setNext(new DepositTransaction(getAccount())).setNext(new CreditTransaction(getAccount()));
        getTransactionHandler().withdraw(money);
    }

    public void transfer(Account replenishAccount, double money)  {
        setTransactionHandler(new DebitTransaction(getAccount()));
        getTransactionHandler().setNext(new DepositTransaction(getAccount())).setNext(new CreditTransaction(getAccount()));
        getTransactionHandler().transfer(replenishAccount, money);
    }

    public void undoTransaction()  {
        getAccount().getCareTaker().restore();
    }
}

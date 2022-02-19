package ru.itmo.banks.main.transaction;

import ru.itmo.banks.main.account.Account;

public interface ITransactionHandler {

    ITransactionHandler setNext(ITransactionHandler handler);
    void replenish(double sum) throws Exception;
    void withdraw(double sum) throws Exception;
    void transfer(Account replenishAccount, double sum) throws Exception;
    void undoTransaction() throws Exception;
}

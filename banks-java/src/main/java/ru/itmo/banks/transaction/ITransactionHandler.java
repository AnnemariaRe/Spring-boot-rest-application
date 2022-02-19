package ru.itmo.banks.transaction;

import ru.itmo.banks.account.Account;

public interface ITransactionHandler {

    ITransactionHandler setNext(ITransactionHandler handler);
    void replenish(double sum) ;
    void withdraw(double sum) ;
    void transfer(Account replenishAccount, double sum) ;
    void undoTransaction() ;
}

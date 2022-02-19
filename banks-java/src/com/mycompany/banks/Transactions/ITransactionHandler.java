package com.mycompany.banks.Transactions;

import com.mycompany.banks.Accounts.Account;

public interface ITransactionHandler {

    ITransactionHandler setNext(ITransactionHandler handler);
    void replenish(double sum) throws Exception;
    void withdraw(double sum) throws Exception;
    void transfer(Account replenishAccount, double sum) throws Exception;
    void undoTransaction() throws Exception;
}

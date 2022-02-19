package com.mycompany.banks.Snapshot;

public class Snapshot {
    private final double Balance;

    public Snapshot(double balance) {
        Balance = balance;
    }

    public final double getState() {
        return Balance;
    }

}

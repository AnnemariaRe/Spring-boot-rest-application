package ru.itmo.banks.exception;

public class NullOrNegativeBanksException extends RuntimeException {

    public NullOrNegativeBanksException(String message) {
        super(message);
    }

}

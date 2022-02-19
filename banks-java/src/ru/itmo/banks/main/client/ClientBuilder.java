package ru.itmo.banks.main.client;

import ru.itmo.banks.main.exception.BanksException;

public class ClientBuilder implements IClientBuilder {

    private Client client;

    public IClientBuilder setNameAndSurname(String name, String surname) {
        client = new Client(name, surname);
        return this;
    }

    public IClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public IClientBuilder setPassportId(int passportId) {
        client.setPassportId(passportId);
        return this;
    }

    public Client build() throws Exception {
        if (client.getName().isEmpty() || client.getSurname().isEmpty())
        {
            throw new BanksException("Cannot create client without name or surname");
        }

        var currentClient = client;
        client.setAddress(null);
        client.setPassportId(0);

        return currentClient;
    }
}

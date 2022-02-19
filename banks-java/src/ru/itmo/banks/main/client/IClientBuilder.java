package ru.itmo.banks.main.client;

public interface IClientBuilder
{
    IClientBuilder setNameAndSurname(String name, String surname);
    IClientBuilder setAddress(String address);
    IClientBuilder setPassportId(int passportId);
    Client build() throws Exception;
}


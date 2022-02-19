package com.mycompany.banks.Clients;

public interface IClientBuilder
{
    IClientBuilder setNameAndSurname(String name, String surname);
    IClientBuilder setAddress(String address);
    IClientBuilder setPassportId(int passportId);
    Client build() throws Exception;
}


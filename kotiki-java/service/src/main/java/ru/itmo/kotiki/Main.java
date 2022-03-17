package ru.itmo.kotiki;

import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.model.Kotik;
import ru.itmo.kotiki.model.Owner;
import ru.itmo.kotiki.service.OwnerService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(final String[] args) {
        OwnerService ownerService = new OwnerService();

        Owner owner = new Owner();
        owner.setName("Annemarija");
        owner.setBirthdayDate(new Date(System.currentTimeMillis()));

        Kotik kotik = new Kotik();
        kotik.setName("Murka");
        kotik.setColor(Color.BLACK);
        kotik.setBreed("sphynx");
        kotik.setBirthdayDate(new Date(System.currentTimeMillis()));
        kotik.setOwner(owner);

        Kotik kotik2 = new Kotik();
        kotik2.setName("Bob");
        kotik2.setColor(Color.CREAM);
        kotik2.setBreed("sphynx");
        kotik2.setBirthdayDate(new Date(System.currentTimeMillis()));
        kotik2.setOwner(owner);

        List<Kotik> kotiki = new ArrayList<>();
        kotiki.add(kotik);
        kotik.setFriends(kotiki);
        kotiki.add(kotik2);
        owner.setKotiki(kotiki);

        ownerService.addOwner(owner);
    }
}

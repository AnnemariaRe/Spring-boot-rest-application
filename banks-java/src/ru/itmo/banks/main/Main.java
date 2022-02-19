package ru.itmo.banks.main;

import ru.itmo.banks.main.bank.Bank;
import ru.itmo.banks.main.bank.CentralBank;
import ru.itmo.banks.main.client.Client;
import ru.itmo.banks.main.client.ClientBuilder;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        CentralBank service = new CentralBank();
        HashMap<Integer, Double> interests;
        Bank bank;
        String bankName;
        Client client;
        String line = null;
        String name;
        String surname;
        String account;
        String address = null;
        int passport = 0;
        double money;
        double debitInterest;
        int amount;
        double interest;
        double commission;
        int limit;
        Scanner in = new Scanner(System.in);


        while (!Objects.equals(line, "9")) {

            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Welcome to the bank system!");
            System.out.println("---------------------------");
            System.out.println("You can choose an action:");
            System.out.println("1 - create new bank");
            System.out.println("2 - add new client");
            System.out.println("3 - add an account to the client");
            System.out.println("4 - subscribe to account updates");
            System.out.println("5 - make some transaction");
            System.out.println("6 - show available banks");
            System.out.println("7 - show all clients");
            System.out.println("8 - show account balance");
            System.out.println("9 - exit");
            System.out.print(">  ");
            line = in.nextLine();

            switch (line) {
                case "1" -> {
                    System.out.println("Please enter bank name: ");
                    name = in.nextLine();
                    System.out.println("Please enter debit interest: ");
                    debitInterest = in.nextDouble();
                    System.out.println("Please enter deposit interests: ");
                    interests = new HashMap<>();

                    for (int i = 0; i < 3; i++) {
                        System.out.println("---------------------------");
                        System.out.println("Enter amount of money: ");
                        amount = in.nextInt();
                        System.out.println("Enter interest for this amount:  ");
                        interest = in.nextDouble();
                        interests.put(amount, interest);
                    }

                    System.out.println("---------------------------");
                    System.out.println("Enter credit commission: ");
                    commission = in.nextInt();
                    System.out.println("Enter credit limit: ");
                    limit = in.nextInt();
                    System.out.println("Creating bank .....");
                    System.out.println(service.createBank(name, debitInterest, interests, commission, limit));
                }
                case "2" -> {
                    if (service.getBanks().isEmpty()) {
                        System.out.println("There are no available banks :(");
                        break;
                    }

                    System.out.println("Please enter name: ");
                    name = in.nextLine();
                    System.out.println("Please enter surname:  ");
                    surname = in.nextLine();

                    System.out.println("Do you want to add address and passport id? y/n");
                    if (in.nextLine().equals("y")) {
                        System.out.println("Please enter address: ");
                        address = in.nextLine();
                        System.out.println("Please enter passport id: ");
                        passport = in.nextInt();
                    }

                    System.out.println("---------------------------");
                    System.out.println("Which bank do you want to choose?");
                    System.out.println("Available banks: ");
                    for (var b : service.getBanks()) {
                        System.out.printf("%1$s%n", b.getName());
                    }

                    System.out.println("Please enter bank name: ");
                    bankName = in.nextLine();
                    System.out.println("Creating new client .....");
                    client = new Client(name, surname);

                    for (var b : service.getBanks()) {
                        if (Objects.equals(bankName, b.getName())) {
                            client = (new ClientBuilder())
                                    .setNameAndSurname(name, surname)
                                    .setAddress(address)
                                    .setPassportId(passport).build();
                            service.addClientToBank(client, b.getId());
                        }
                    }
                    System.out.println(client.toString());
                }

                case "3" -> {
                    if (service.getBanks() == null) {
                        System.out.println("There are no available banks and clients :(");
                        break;
                    }

                    System.out.println("Please enter account type: ");
                    System.out.println("{debit, deposit, credit}");
                    account = in.nextLine();
                    System.out.println("Please enter bank name: ");
                    System.out.println("Available banks:");
                    for (var b : service.getBanks())
                    {
                        System.out.printf("%1$s%n", b.getName());
                    }

                    System.out.println("---------------------------");
                    bankName = in.nextLine();
                    System.out.println("Please enter client name:");
                    name = in.nextLine();
                    System.out.println("Please enter client surname:");
                    surname = in.nextLine();

                    System.out.println("Please enter account balance: ");
                    money = in.nextDouble();

                    System.out.println("Creating account .....");
                    String finalBankName2 = bankName;
                    var bank2 = service
                            .getBanks()
                            .stream()
                            .filter(b -> Objects.equals(b.getName(), finalBankName2))
                            .findFirst()
                            .orElse(null);

                    String finalName = name;
                    String finalSurname = surname;
                    assert bank2 != null;
                    var acc = service
                            .createAccount(bank2.getClients()
                                                                .keySet()
                                                                .stream()
                                                                .filter(cl -> Objects.equals(cl.getName(), finalName) && Objects.equals(cl.getSurname(), finalSurname))
                                                                .findFirst()
                                                                .orElse(null), Objects.requireNonNull(bank2). getId(), account, money);

                    System.out.println(acc.toString());
                }

                case "4" -> {
                    if (service.getBanks().isEmpty()) {
                        System.out.println("There are no available banks :(");
                        break;
                    }

                    System.out.println("Please enter bank name from what you want to receive updates: ");
                    bankName = in.nextLine();
                    System.out.println("Please enter your name: ");
                    name = in.nextLine();
                    System.out.println("Please enter you surname: ");
                    surname = in.nextLine();

                    String finalBankName3 = bankName;
                    bank = service
                            .getBanks()
                            .stream()
                            .filter(b -> Objects.equals(b.getName(), finalBankName3))
                            .findFirst()
                            .orElse(null);

                    String finalName1 = name;
                    String finalSurname1 = surname;
                    assert bank != null;
                    client = bank
                            .getClients()
                            .keySet()
                            .stream()
                            .filter(c -> Objects.equals(c.getName(), finalName1)
                                    && Objects.equals(c.getSurname(), finalSurname1))
                            .findFirst()
                            .orElse(null);

                    bank.subscribeUser(client);
                    System.out.println("---------------------------");
                    System.out.println("You have subscribed to notifications about updates in accounts!");
                }

                case "5" -> {
                    if (service.getBanks().isEmpty()) {
                        System.out.println("There are no available banks :(");
                        break;
                    }

                    System.out.println("What transaction do you want to do?");
                    System.out.println("{replenish, withdraw, transfer}");
                    var trans = in.nextLine();

                    System.out.println("How much money?");
                    money = in.nextDouble();

                    System.out.println("Please enter bank name:");
                    System.out.println("Available banks:");
                    for (var b : service.getBanks())
                    {
                        System.out.printf("%1$s, ", b.getName());
                    }

                    System.out.println("---------------------------");
                    bankName = in.nextLine();
                    System.out.println("Please enter client name:");
                    name = in.nextLine();
                    System.out.println("Please enter client surname:");
                    surname = in.nextLine();

                    String finalBankName1 = bankName;
                    var bank1 = service.getBanks()
                            .stream()
                            .filter(b -> Objects.equals(b.getName(), finalBankName1))
                            .findFirst()
                            .orElse(null);

                    String finalName2 = name;
                    String finalSurname2 = surname;
                    assert bank1 != null;
                    var client1 = bank1
                            .getClients()
                            .keySet()
                            .stream()
                            .filter(cl -> Objects.equals(cl.getName(), finalName2) && Objects.equals(cl.getSurname(), finalSurname2))
                            .findFirst()
                            .orElse(null);

                    System.out.println("Please enter account type: ");
                    System.out.println("{debit, deposit, credit}");
                    account = in.nextLine();

                    String finalAccount = account;
                    var acc = bank1
                            .getAccounts()
                            .stream()
                            .filter(ac -> ac.getOwner() == client1 && Objects.equals(ac.getAccountType(), finalAccount))
                            .findFirst()
                            .orElse(null);

                    System.out.println("Making a transaction.....");

                    System.out.println("---------------------------");
                    assert acc != null;
                    System.out.printf("Money before: %1$s%n", acc.getBalance());

                    switch (trans) {
                        case "replenish" -> bank1.replenishMoney(acc.getId(), money);
                        case "withdraw" -> bank1.withdrawMoney(acc.getId(), money);
                        case "transfer" -> {
                            System.out.println("To what account do you want to transfer money?");
                            var to = in.nextLine();
                            System.out.println("Enter client name:");
                            var name1 = in.nextLine();
                            System.out.println("Enter client surname:");
                            var surname1 = in.nextLine();

                            System.out.println("Please enter bank name:");
                            System.out.println("Available banks:");
                            for (var b : service.getBanks()) {
                                System.out.printf("%1$s%n ", b.getName());
                            }

                            bankName = in.nextLine();
                            String finalBankName = bankName;
                            var bank2 = service
                                    .getBanks()
                                    .stream()
                                    .filter(b -> Objects.equals(b.getName(), finalBankName))
                                    .findFirst()
                                    .orElse(null);

                            String finalAccount1 = account;
                            assert bank2 != null;
                            var accTo = bank2
                                    .getAccounts()
                                    .stream()
                                    .filter(ac -> ac.getOwner() == client1 && Objects.equals(ac.getAccountType(), finalAccount1))
                                    .findFirst()
                                    .orElse(null);

                            assert accTo != null;
                            bank1.transferMoney(acc.getId(), accTo.getId(), money);
                        }
                    }

                    System.out.println("---------------------------");
                    System.out.printf("Money now: %1$s%n", acc.getBalance());
                }

                case "6" -> {
                    System.out.println("Available banks:");

                    for (var b : service.getBanks())
                    {
                        System.out.printf("%1$s%n", b.getName());
                    }
                }

                case "7" -> {
                    System.out.println("Please enter bank name");
                    bankName = in.nextLine();
                    String finalBankName4 = bankName;
                    System.out.println("---------------------------");
                    System.out.println(Objects.requireNonNull(service.getBanks()
                            .stream()
                            .filter(b -> Objects.equals(b.getName(), finalBankName4))
                            .findFirst()
                            .orElse(null)));
                }

                case "8" -> {
                    System.out.println("Please enter bank name");
                    bankName = in.nextLine();
                    System.out.println("Please enter client name:");
                    name = in.nextLine();
                    System.out.println("Please enter client surname:");
                    surname = in.nextLine();

                    System.out.println("Please enter account type: ");
                    System.out.println("Debit, Deposit, Credit");
                    account = new Scanner(System.in).nextLine();
                    System.out.println("---------------------------");

                    String finalBankName5 = bankName;
                    bank = service.getBanks()
                            .stream()
                            .filter(b -> Objects.equals(b.getName(), finalBankName5))
                            .findFirst()
                            .orElse(null);

                    String finalName3 = name;
                    String finalSurname3 = surname;
                    assert bank != null;
                    client = bank.getClients()
                            .keySet()
                            .stream()
                            .filter(c -> Objects.equals(c.getName(), finalName3) && Objects.equals(c.getSurname(), finalSurname3))
                            .findFirst()
                            .orElse(null);

                    String finalAccount2 = account;
                    Client finalClient = client;
                    System.out.println(bank.getAccounts()
                            .stream()
                            .filter(acc -> acc.getOwner() == finalClient && Objects.equals(acc.getAccountType(), finalAccount2))
                            .findFirst()
                            .orElse(null));
                }

                case "9" -> {
                    return;
                }

                default -> System.out.println("Incorrect input, please try again");
            }
        }
    }
}
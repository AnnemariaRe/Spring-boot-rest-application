package ru.itmo.banks.client;

public class Client {

    public Client(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    private String name;
    private String surname;
    private String address;
    private int passportId;
    private boolean isVerified;

    public final String getName() { return name; }
    protected final void setName(String value) { name = value; }

    public final String getSurname() { return surname; }
    protected final void setSurname(String value) { surname = value; }

    public final String getAddress() { return address; }
    protected final void setAddress(String value) { address = value; }

    public final int getPassportId() { return passportId; }
    protected final void setPassportId(int value) { passportId = value; }

    public final boolean getIsVerified() { return (getPassportId() > 0 && !getAddress().isEmpty()); }

    public final void update(String eventType) {
        System.out.printf("Client %s receive a notification", getName());
    }

    @Override
    public String toString()
    {
        return String.format("Name: %1$s", getName()) + System.lineSeparator() +
                String.format("Surname: %1$s", getSurname()) + System.lineSeparator() +
                String.format("Address: %1$s", getAddress()) + System.lineSeparator() +
                String.format("Passport id: %1$s", getPassportId()) + System.lineSeparator();
    }

}

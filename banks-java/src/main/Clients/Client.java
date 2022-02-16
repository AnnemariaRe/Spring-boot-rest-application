package main.Clients;

public class Client {

    public Client(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    private String Name;
    public final String getName() { return Name; }
    protected final void setName(String name) { Name = name; }

    private String Surname;
    public final String getSurname() { return Surname; }
    protected final void setSurname(String surname) { Surname = surname; }

    private String Address;
    public final String getAddress() { return Address; }
    protected final void setAddress(String address) { Address = address; }

    private int PassportId;
    public final int getPassportId() { return PassportId; }
    protected final void setPassportId(int passportId) { PassportId = passportId; }

    private boolean IsVerified;
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

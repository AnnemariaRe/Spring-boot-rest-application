package ru.itmo.banks.main.observer;

import ru.itmo.banks.main.client.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<String, List<Client>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Client listener) {
        List<Client> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, Client listener) {
        List<Client> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType) {
        List<Client> users = listeners.get(eventType);
        for (Client listener : users) {
            listener.update(eventType);
        }
    }
}

package org.annemariare.cats.messaging;


import java.io.Serializable;

public class NameResponse implements Serializable {
    private String name;
    private String username;

    @Override
    public String toString() {
        return "NameResponse{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public NameResponse() {
    }

    public NameResponse(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

}


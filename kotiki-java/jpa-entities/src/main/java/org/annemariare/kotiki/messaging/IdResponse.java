package org.annemariare.kotiki.messaging;


import java.io.Serializable;

public class IdResponse implements Serializable {
    private Long id;
    private String username;

    @Override
    public String toString() {
        return "IdResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public IdResponse() {
    }

    public IdResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}

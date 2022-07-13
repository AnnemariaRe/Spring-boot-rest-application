package org.annemariare.cats.dto;

import java.io.Serializable;

public class AuthDto implements Serializable {
    private final String username;
    private final String password;

    public AuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

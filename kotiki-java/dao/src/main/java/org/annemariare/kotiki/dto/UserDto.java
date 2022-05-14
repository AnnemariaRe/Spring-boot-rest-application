package org.annemariare.kotiki.dto;

import org.annemariare.kotiki.enums.Role;

import java.io.Serializable;

public class UserDto implements Serializable {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final OwnerDto owner;
    private final Role role;

    public UserDto(Long id, String username, String email, String password, OwnerDto owner, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.owner = owner;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public Role getRole() {
        return role;
    }
}

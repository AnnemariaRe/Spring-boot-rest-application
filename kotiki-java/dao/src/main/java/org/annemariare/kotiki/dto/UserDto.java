package org.annemariare.kotiki.dto;

import org.annemariare.kotiki.enums.Role;

import java.io.Serializable;

public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private OwnerDto owner;
    private Role role;

    public UserDto(Long id, String username, String email, String password, OwnerDto owner, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.owner = owner;
        this.role = role;
    }

    public UserDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

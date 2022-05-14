package org.annemariare.kotiki.entity;

import org.annemariare.kotiki.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String email, String password, Role role, OwnerEntity owner) {
        this.owner = owner;
        this.setId(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

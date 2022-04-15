package org.annemariare.kotiki.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.annemariare.kotiki.enums.Color;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kotiki")
public class KotikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdayDate;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "first_kotik_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_kotik_id")})
    private List<KotikEntity> friends;

    public KotikEntity() {}

    public KotikEntity(Long id, String name, Date date, String breed, Color color, OwnerEntity owner) {
        this.id = id;
        this.name = name;
        this.birthdayDate = date;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    public void setFriend(KotikEntity kotik) {
        if (getFriends() == null) {
            this.friends = new ArrayList<>();
            this.friends.add(kotik);
        } else {
            getFriends().add(kotik);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public List<KotikEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<KotikEntity> friends) {
        this.friends = friends;
    }
}

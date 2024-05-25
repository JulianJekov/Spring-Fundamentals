package com.resellerapp.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 20)
    private String username;

    @Column(nullable = false)
    @Length(min = 3)
    //removing max 20 for password length because it will be encoded before the save
    private String password;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "createdBy")
    private List<Offer> offers;

    @OneToMany(mappedBy = "boughtBy")
    private List<Offer> boughtOffers;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public User setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public List<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public User setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}

package com.paintingscollectors.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Painting> paintings;

    @ManyToMany()
    private List<Painting> favouritePaintings;

    @ManyToMany
    private List<Painting> ratedPaintings;

    public User() {
    }

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

    public List<Painting> getPaintings() {
        return paintings;
    }

    public User setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
        return this;
    }

    public List<Painting> getFavouritePaintings() {
        return favouritePaintings;
    }

    public User setFavouritePaintings(List<Painting> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
        return this;
    }

    public List<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public User setRatedPaintings(List<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }
}

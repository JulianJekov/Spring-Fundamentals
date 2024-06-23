package com.paintingscollectors.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private boolean logged;

    public CurrentUser() {
    }

    public void login(String username, Long id) {
        this.logged = true;
        this.username = username;
        this.id = id;
    }

    public void logout() {
        this.logged = false;
        this.username = null;
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLogged() {
        return logged;
    }

    public CurrentUser setLogged(boolean logged) {
        this.logged = logged;
        return this;
    }
}

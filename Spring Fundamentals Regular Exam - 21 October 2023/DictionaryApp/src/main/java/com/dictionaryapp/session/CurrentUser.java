package com.dictionaryapp.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
@Getter
@Setter
@NoArgsConstructor
public class CurrentUser {

    private Long id;

    private String username;

    private boolean isLogged;


    public void logout() {
        this.isLogged = false;
        this.id = null;
        this.username = null;
    }

    public void login(Long id, String username) {
        this.isLogged = true;
        this.id = id;
        this.username = username;
    }

}

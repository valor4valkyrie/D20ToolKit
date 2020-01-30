package com.d20.model;

import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@JsonComponent
public class Player implements Serializable {

    private String playerName;
    private String playerDisplayName;
    private String password;
    private String playerEmail;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerDisplayName() {
        return playerDisplayName;
    }

    public void setPlayerDisplayName(String playerDisplayName) {
        this.playerDisplayName = playerDisplayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }
}

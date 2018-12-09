package com.example.reta.user_diary;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_login")
    private String id_login;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public User(String id_login, String username, String password) {
        this.id_login = id_login;
        this.username = username;
        this.password = password;
    }

    public String getId_login() {
        return id_login;
    }

    public void setId_login(String id_login) {
        this.id_login = id_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

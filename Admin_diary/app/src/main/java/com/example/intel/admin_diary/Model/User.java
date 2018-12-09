package com.example.intel.admin_diary.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_login")
    private String idLogin;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nama")
    private String nama;
    @SerializedName("photo_url")
    private String photoUrl;
    private String action;

    public User(String idLogin, String username, String password, String nama, String photoUrl, String action) {
        this.idLogin = idLogin;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.photoUrl = photoUrl;
        this.action = action;
    }

    public String getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(String idLogin) {
        this.idLogin = idLogin;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

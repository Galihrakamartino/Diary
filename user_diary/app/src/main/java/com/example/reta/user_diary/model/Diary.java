package com.example.reta.user_diary.model;

import com.google.gson.annotations.SerializedName;

public class Diary {
    @SerializedName("id_diary")
    private String id_diary;
    @SerializedName("judul")
    private String judul;
    @SerializedName("id_lokasi")
    private String id_lokasi;
    @SerializedName("lokasi")
    private String lokasi;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("isi_diary")
    private String isi_diary;
    @SerializedName("tgl_diary")
    private String tgl_diary;
    @SerializedName("mood")
    private String mood;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("gambar")
    private String gambar;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Diary(String id_diary, String judul, String id_lokasi, String id_user, String isi_diary, String tgl_diary, String mood, String gambar) {
        this.id_diary = id_diary;
        this.judul = judul;
        this.id_lokasi = id_lokasi;
        this.id_user = id_user;
        this.isi_diary = isi_diary;
        this.tgl_diary = tgl_diary;
        this.mood = mood;
        this.gambar = gambar;
    }

    public String getId_diary() {
        return id_diary;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setId_diary(String id_diary) {
        this.id_diary = id_diary;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getIsi_diary() {
        return isi_diary;
    }

    public void setIsi_diary(String isi_diary) {
        this.isi_diary = isi_diary;
    }

    public String getTgl_diary() {
        return tgl_diary;
    }

    public void setTgl_diary(String tgl_diary) {
        this.tgl_diary = tgl_diary;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}


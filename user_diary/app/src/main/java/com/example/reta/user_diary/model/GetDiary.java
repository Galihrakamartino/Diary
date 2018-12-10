package com.example.reta.user_diary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDiary {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Diary> listDiary;
    @SerializedName("message")
    String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Diary> getListDiary() {
        return listDiary;
    }

    public void setListDiary(List<Diary> listDiary) {
        this.listDiary = listDiary;
    }

}

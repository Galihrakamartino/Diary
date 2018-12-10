package com.example.reta.user_diary.Adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reta.user_diary.R;
import com.example.reta.user_diary.Rest.ApiClient;
import com.example.reta.user_diary.Rest.ApiInterface;
import com.example.reta.user_diary.model.Diary;
import com.example.reta.user_diary.model.GetDiary;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {
    List<Diary> listDiary;
    Callback<GetDiary> context;
    ApiInterface mApiInterface;

    public DiaryAdapter(List<Diary> listDiary, Callback<GetDiary> context) {
        this.listDiary = listDiary;
        this.context = context;
    }



    @Override
    public DiaryAdapter.DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_diary, parent, false);
        DiaryViewHolder mHolder = new DiaryViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.DiaryViewHolder holder,final int position) {
        holder.id.setText(listDiary.get(position).getId_diary());
        holder.judul.setText(listDiary.get(position).getJudul());
        holder.lokasi.setText(listDiary.get(position).getLokasi());
        holder.tgl.setText(listDiary.get(position).getTgl_diary());
        holder.mood.setText(listDiary.get(position).getKeterangan());
        if (listDiary.get(position).getGambar() != "" ){
            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listDiary.get(position).getGambar()).into(holder.gambar);
//            Glide.with(holder.itemView.getContext()).load(listMood.get(position).getMood()).into(holder.mood);
        } else {
            //Picassp.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder.mPhotoURL);
            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder.gambar);

        }


    }

    @Override
    public int getItemCount() {
        return listDiary.size();
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder{
        ImageView gambar;
        TextView id,lokasi, judul, mood, isi, tgl;
        public DiaryViewHolder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.imgDiary);
            judul = (TextView) itemView.findViewById(R.id.judul);
            id = (TextView) itemView.findViewById(R.id.idDiary);
            lokasi = (TextView) itemView.findViewById(R.id.lokasi);
            tgl = (TextView) itemView.findViewById(R.id.tgl);
            mood = (TextView) itemView.findViewById(R.id.mood);
        }
    }
}

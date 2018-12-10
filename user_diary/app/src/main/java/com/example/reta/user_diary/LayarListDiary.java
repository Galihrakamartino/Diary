package com.example.reta.user_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.reta.user_diary.Adapter.DiaryAdapter;
import com.example.reta.user_diary.Rest.ApiClient;
import com.example.reta.user_diary.Rest.ApiInterface;
import com.example.reta.user_diary.model.Diary;
import com.example.reta.user_diary.model.GetDiary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListDiary extends AppCompatActivity {

    Button btGet;
    ApiInterface mApiInterface;
    FloatingActionButton btAddData;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_diary);

        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (FloatingActionButton) findViewById (R.id.btAdd );
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mlLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);


        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("data_login", MODE_PRIVATE);
                String idUser = pref.getString("id_login", "");
                Call<GetDiary> diaryCall = mApiInterface.getDiary();
                diaryCall.enqueue(new Callback<GetDiary>() {
                    @Override
                    public void onResponse(Call<GetDiary> call, Response<GetDiary> response) {
                        List<Diary> diaryList = response.body().getListDiary();


                        mAdapter = new DiaryAdapter(diaryList,this);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetDiary> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), layarInsertDiary.class);
                startActivity(intent);
            }
        });
    }
}

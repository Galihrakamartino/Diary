package com.example.intel.admin_diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.intel.admin_diary.Adapter.MyAdapter;
import com.example.intel.admin_diary.Model.GetLokasi;
import com.example.intel.admin_diary.Model.Lokasi;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.example.intel.admin_diary.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layarutama extends AppCompatActivity {

    Button btGet;
    ApiInterface mApiInterface;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layarutama);

        btGet = (Button) findViewById(R.id.btGet);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mlLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlLayoutManager);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetLokasi> lokasiCall = mApiInterface.getLokasi();
                lokasiCall.enqueue(new Callback<GetLokasi>() {
                    @Override
                    public void onResponse(Call<GetLokasi> call, Response<GetLokasi> response) {
                        List<Lokasi> lokasiList = response.body().getListDataLokasi();
                        Log.d("Retrofit Get", "Jumlah data lokasi: " + String.valueOf(lokasiList.size()));

                        mAdapter = new MyAdapter(lokasiList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetLokasi> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });


    }
}

package com.example.reta.user_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reta.user_diary.Rest.ApiClient;
import com.example.reta.user_diary.Rest.ApiInterface;
import com.example.reta.user_diary.model.GetRegister;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {
    EditText edtIdLogin, edtUsernam, edtPassword;
    Button btRegister, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);


        edtIdLogin = findViewById(R.id.txt_idLogincontent);
        edtUsernam = findViewById(R.id.txt_usernamecontent);
        edtPassword = findViewById(R.id.txt_passwordcontent);

        btRegister = findViewById(R.id.btn_register);
        btBack = findViewById(R.id.btn_back);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtIdLogin.getText().toString().isEmpty() || edtUsernam.getText().toString().isEmpty() ||
                        edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegistrasiActivity.this, "Harap isi semua", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser();
                }

            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mIntent);
            }
        });
    }

    private void registerUser() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        RequestBody reqIdLogin = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (edtIdLogin.getText().toString()));
        RequestBody requsername = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (edtUsernam.getText().toString()));
        RequestBody reqpassword = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (edtPassword.getText().toString()));

        Call<GetRegister> mRegisterCall = mApiInterface.postRegister(reqIdLogin ,requsername, reqpassword);
        mRegisterCall.enqueue(new Callback<GetRegister>() {
            @Override
            public void onResponse(Call<GetRegister> call, Response<GetRegister> response) {
                Log.d("Register", response.body().getStatus());
                if (response.body().getStatus().equals("failed")){
                    Toast.makeText(RegistrasiActivity.this, "Gagal Buat Akun", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistrasiActivity.this, "Akun Berhasil Dibuat, Silahkan Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetRegister> call, Throwable t) {
                Log.d("Login", t.getMessage());
            }
        });

        openLogin();

    }

    private void openLogin() {
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mIntent);
    }
}

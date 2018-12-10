package com.example.reta.user_diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reta.user_diary.Rest.ApiClient;
import com.example.reta.user_diary.Rest.ApiInterface;
import com.example.reta.user_diary.model.GetLogin;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin,btnregis;
    ApiInterface mApiInterface;
    String id_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.txt_username);
        etPassword = (EditText) findViewById(R.id.txt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnregis = (Button) findViewById(R.id.btn_regis);


    }
    private void login() {
        String username = this.etUsername.getText().toString();
        String password = this.etPassword.getText().toString();
        this.checkCredentials(username, password);

    }

    private void openHome() {
        Intent intent = new Intent(this.getApplicationContext(), LayarListDiary.class);
        intent.putExtra("id_user",id_login);
        this.startActivity(intent);
    }

    private void saveCredentials() {
        SharedPreferences handler = this.getSharedPreferences("data_login", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.etUsername.getText().toString());
        editor.putString("password", this.etPassword.getText().toString());
        editor.putString("id_login", this.id_login);
        editor.apply();

    }

    private void checkCredentials(String username, String password) {

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestBody reqUsername = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (username.toString().isEmpty())?"":username.toString());
        RequestBody reqPassword = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (password.toString().isEmpty())?"":password.toString());

        Call<GetLogin> mLoginCall = mApiInterface.postLogin(reqUsername, reqPassword);
        mLoginCall.enqueue(new Callback<GetLogin>() {
            @Override
            public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                Log.d("Login", response.body().getStatus()+" "+response.body().getResult());
                if (response.body().getStatus().equals("unfriended")){
                    Toast.makeText(MainActivity.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }else{
                    id_login = response.body().getResult();
                    saveCredentials();
                    openHome();
                }
            }

            @Override
            public void onFailure(Call<GetLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Coba Lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void button_onClickLogin(View view) {
        this.login();
    }

    public void button_onClickRegister(View view) {

        Intent intent = new Intent(getApplicationContext(), RegistrasiActivity.class);

        startActivity(intent);

    }
}

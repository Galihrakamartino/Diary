package com.example.reta.user_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reta.user_diary.Rest.ApiClient;
import com.example.reta.user_diary.Rest.ApiInterface;
import com.example.reta.user_diary.model.GetDiary;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layarInsertDiary extends AppCompatActivity {
    Button btInsert, btAddPhotoId;
    String imagePath = "";
    ImageView mImageView;
    EditText user, lokasi, mood,judul, tgl, isi;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_diary);

        mImageView = (ImageView) findViewById(R.id.img);
        btAddPhotoId = (Button)  findViewById(R.id.addphoto);
        btInsert = (Button) findViewById(R.id.btSimpan);
        SharedPreferences pref = getSharedPreferences("data_login", MODE_PRIVATE);
        String idUser = pref.getString("id_login", "");
        lokasi = (EditText) findViewById(R.id.lokasi);
        mood = (EditText) findViewById(R.id.mood);
        judul = (EditText) findViewById(R.id.judull);
        tgl = (EditText) findViewById(R.id.tanggal);
        isi = (EditText) findViewById(R.id.isi);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("data_login", MODE_PRIVATE);
                String idUser = pref.getString("id_login", "");
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    // Buat file dari image yang dipilih
                    File file = new File(imagePath);

                    // Buat RequestBody instance dari file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

                    // MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody.Part.createFormData("gambar", file.getName(),
                            requestFile);
                }
                RequestBody reqLokasi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (lokasi.getText().toString().isEmpty())?"":lokasi.getText().toString());
                RequestBody reqMood = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (mood.getText().toString().isEmpty())?"":mood.getText().toString());
                RequestBody reqIsi = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (isi.getText().toString().isEmpty())?"":isi.getText().toString());
                RequestBody reqTgl = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (tgl.getText().toString().isEmpty())?"":tgl.getText().toString());
                RequestBody reqJudul = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        (judul.getText().toString().isEmpty())?"":judul.getText().toString());
                RequestBody reqAction = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        "insert");
                RequestBody reqUser = MultipartBody.create(MediaType.parse("multipart/form-data"),
                        idUser);
                Call<GetDiary> postDiaryCall = mApiInterface.postDiary(body, reqUser, reqMood, reqLokasi, reqJudul, reqIsi, reqTgl,reqAction
                );

                postDiaryCall.enqueue(new Callback<GetDiary>() {
                    @Override
                    public void onResponse(Call<GetDiary> call, Response<GetDiary> response) {
                        Log.d("Retrofit","berhasil");
                    }

                    @Override
                    public void onFailure(Call<GetDiary> call, Throwable t) {
                        Log.e("Retrofit","error");
                    }
                });
            }
        });
        btAddPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(
                        galleryIntent,
                        "Pilih foto untuk di-upload");
                startActivityForResult(intentChoose, 10);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(this, "Foto gagal di-load", Toast.LENGTH_LONG).show();
                return;
            }

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

                Picasso.with(this).load(new File(imagePath)).fit().into(mImageView);
//                Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor.close();
            }else{
                Toast.makeText(this, "Foto gagal di-load", Toast.LENGTH_LONG).show();
            }
        }

    }
}

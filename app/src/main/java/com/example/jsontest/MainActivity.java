package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jsontest.API.Menthods;
import com.example.jsontest.model.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        getData=findViewById(R.id.getData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menthods menthods = RetrofitClient.getRetrofitInstance().create(Menthods.class);
                Call<Model> call = menthods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.e(TAG,"onRespone: code: "+response.code());

                       ArrayList<Model.data> data = response.body().getData();

                       for(Model.data data1: data){
                           Log.e(TAG,"onRespone: emails:" + data1.getEmail());
                       }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                            Log.e(TAG,"onFailure"+t.getMessage());
                    }
                });
            }
        });

    }
}
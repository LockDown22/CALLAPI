package com.example.jsontest.API;

import com.example.jsontest.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Menthods {
    @GET("api/users?page=2")
    Call<Model> getAllData();
}

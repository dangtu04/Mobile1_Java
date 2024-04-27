package com.example.dangthanhtu.api;



import com.example.dangthanhtu.Product;
import com.example.dangthanhtu.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {



//    https://fakestoreapi.com/users?sort=desc

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM--dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("users")
    Call<List<User>> getListUsers(@Query("sort") String key);

    @GET("products")
    Call<List<Product>> getProducts();




}

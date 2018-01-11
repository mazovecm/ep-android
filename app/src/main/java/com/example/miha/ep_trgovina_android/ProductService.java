package com.example.miha.ep_trgovina_android;



import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Miha on 8. 01. 2018.
 */

public class ProductService {
    interface RestApi {
        String URL = "http://10.0.2.2:8080/api/";

        @GET("products")
        Call<ProductList> getAll();


        @GET("products/{id}")
        Call<ProductWrapper> get(@Path("id") int id);
    }

    private static RestApi instance;

    public static synchronized RestApi getInstance() {
        if (instance == null) {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(RestApi.class);
        }

        return instance;
    }
}

package com.example.miha.ep_trgovina_android;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements Callback<Product> {
    private static final String TAG = ProductDetailActivity.class.getCanonicalName();

    private Product product;
    private TextView tvProductDetail;
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Log.i("Lokacija: ", "ProductDetailActivity");

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        tvProductDetail = (TextView) findViewById(R.id.tv_product_detail);

        final int id = getIntent().getIntExtra("product_id", 0);

        System.out.println("id = " + id);
        if (id > 0) {
            Log.i("Klic", "api klic");
            System.out.println("ID se enkrat: " + id);
            ProductService.getInstance().get(id).enqueue(this);
        }




    }

    @Override
    public void onResponse(Call<Product> call, Response<Product> response) {
        product = response.body();
        Log.i(TAG, "Got result: " + product);

        if (response.isSuccessful()) {
            System.out.println("Response is successful");
            tvProductDetail.setText("OMG");
            toolbarLayout.setTitle("WTF");
        } else {
            String errorMessage;
            try {
                errorMessage = "An error occurred: " + response.errorBody().string();
            }  catch (IOException e) {
                errorMessage = "An error occurred: error while decoding the error message.";
            }
            Log.e(TAG, errorMessage);
            tvProductDetail.setText(errorMessage);
        }

    }

    @Override
    public void onFailure(Call<Product> call, Throwable t) {
        Log.w(TAG, "Error" + t.getMessage(), t);
    }
}

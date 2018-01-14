package com.example.miha.ep_trgovina_android;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.ViewSwitcher;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements Callback<ProductWrapper> {
    private static final String TAG = ProductDetailActivity.class.getCanonicalName();
    private String url = "http://10.0.2.2:8080";


    private List<String> urls = new ArrayList<String>();
    private Product product;
    private List<ProductImages> images;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private TextView naziv;
    private TextView cena;
    private TextView ocena;
    private TextView opis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        naziv = (TextView) findViewById(R.id.tv_title);
        cena = (TextView) findViewById(R.id.tv_price);
        ocena = (TextView) findViewById(R.id.tv_rating);
        opis = (TextView) findViewById(R.id.tv_description);

        final int id = getIntent().getIntExtra("product", 0);
        Log.i(TAG,"id = " + id);
        if (id > 0) {
            Log.i("Klic", "api klic");
            ProductService.getInstance().get(id).enqueue(this);
        }


    }

    @Override
    public void onResponse(Call<ProductWrapper> call, Response<ProductWrapper> response) {
        product = response.body().getProduct();
        Log.i(TAG, "Got result: " + product);
        images = product.slike;
        Log.i(TAG, "Got images: " + images);

        for (int i = 0; i < images.size(); i++) {
            urls.add(url + images.get(i).getPot());
        }

        if (response.isSuccessful()) {
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            adapter = new ViewPagerAdapter(ProductDetailActivity.this, urls);
            viewPager.setAdapter(adapter);

            naziv.setText(product.getNaziv());
            cena.setText(String.valueOf(product.getCena()) + " €");
            ocena.setText("Ocena: " + String.valueOf(product.getOcena()));
            opis.setText(product.getOpis());

        } else {
            String errorMessage;
            try {
                errorMessage = "An error occurred: " + response.errorBody().string();
            }  catch (IOException e) {
                errorMessage = "An error occurred: error while decoding the error message.";
            }
            Log.e(TAG, errorMessage);
            opis.setText(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<ProductWrapper> call, Throwable t) {
        Log.w(TAG, "Error" + t.getMessage(), t);
    }
}

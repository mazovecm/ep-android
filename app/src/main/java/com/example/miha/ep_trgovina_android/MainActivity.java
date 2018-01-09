package com.example.miha.ep_trgovina_android;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.miha.ep_trgovina_android.ProductService.getInstance;

public class MainActivity extends AppCompatActivity implements Callback<ProductList> {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private SwipeRefreshLayout container;
    private ListView list;
    private ProductAdapter adapter;
    private ProductList products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.items);

        if (savedInstanceState != null && savedInstanceState.getSerializable("products") != null) {
            adapter = (ProductAdapter) savedInstanceState.getSerializable("products");
        }

        else {
            adapter = new ProductAdapter(this);
        }
        list.setAdapter(adapter);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Product product = adapter.getItem(i);

                System.out.println(product);
                if (product != null) {
                    System.out.println("ID = " + product.getId());
                    final Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra("product", product);
                    startActivity(intent);
                }
            }
        });
        container = (SwipeRefreshLayout) findViewById(R.id.container);
        container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductService.getInstance().getAll().enqueue(MainActivity.this);
            }
        });


        if (savedInstanceState == null) {
            Log.i("Klic", "api klic");
            ProductService.getInstance().getAll().enqueue(MainActivity.this);
        }


    }

    @Override
    public void onResponse(Call<ProductList> call, Response<ProductList> response) {
        products = response.body();
        if (response.isSuccessful()) {
            adapter.clear();
            adapter.addAll(products.getProducts());
            Log.i(TAG, "Products: ");
        }
    }

    @Override
    public void onFailure(Call<ProductList> call, Throwable t) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("products", adapter);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter = (ProductAdapter) savedInstanceState.get("products");

    }
}

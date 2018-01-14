package com.example.miha.ep_trgovina_android;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    private Button prijava;
    private Button odjava, podatki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.items);

        if (savedInstanceState != null && savedInstanceState.getSerializable("products") != null) {
            adapter = (ProductAdapter) savedInstanceState.getSerializable("products");
        } else {
            adapter = new ProductAdapter(this);
        }

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Product product = adapter.getItem(i);
                Log.i(TAG, "product: " + product);

                if (product != null) {
                    Log.i(TAG, "product_id: " + product.getId());
                    final Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra("product", product.id);
                    startActivity(intent);
                }
            }
        });

        container = (SwipeRefreshLayout) findViewById(R.id.container);
        container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                Log.i("Klic", "api klic");
                ProductService.getInstance().getAll().enqueue(MainActivity.this);
            }
        });

        prijava = (Button) findViewById(R.id.prijava);
        odjava = (Button) findViewById(R.id.odjava);
        podatki = (Button) findViewById(R.id.podatki);
        final MyApplicationObject app = (MyApplicationObject) getApplication();

        if (app.user == null) {
            odjava.setVisibility(View.GONE);
            podatki.setVisibility(View.GONE);
            prijava.setVisibility(View.VISIBLE);
            prijava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }
        else {
            app.user.toString();
            prijava.setVisibility(View.GONE);
            odjava.setVisibility(View.VISIBLE);
            podatki.setVisibility(View.VISIBLE);
            odjava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    app.user = null;
                    startActivity(new Intent(MainActivity.this, MainActivity.class));

                }
            });
            podatki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, UserDataActivity.class));
                }
            });
        }



        if (savedInstanceState == null) {
            Log.i("Klic", "api klic");
            ProductService.getInstance().getAll().enqueue(MainActivity.this);
        }


    }

    @Override
    public void onResponse(Call<ProductList> call, Response<ProductList> response) {
        products = response.body();
        Log.i(TAG, "products: " + products);
        if (response.isSuccessful()) {
            adapter.clear();
            adapter.addAll(products.getProducts());
        } else {
            String errorMessage;
            try {
                errorMessage = "Error: " + response.errorBody().string();
            } catch (IOException e) {
                errorMessage = "Error";
            }
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            Log.e(TAG, errorMessage);
        }
        container.setRefreshing(false);
    }

    @Override
    public void onFailure(Call<ProductList> call, Throwable t) {
        Log.w(TAG, "Error; " + t.getMessage(), t);
        container.setRefreshing(false);
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
package com.example.miha.ep_trgovina_android;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.items);
        adapter = new ProductAdapter(this);

        list.setAdapter(adapter);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Product product = adapter.getItem(i);
                if (product != null) {
                    final Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra("product_id", product.id);
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

        Log.i("Klic", "api klic");
        ProductService.getInstance().getAll().enqueue(MainActivity.this);


    }

    @Override
    public void onResponse(Call<ProductList> call, Response<ProductList> response) {
        final ProductList products = response.body();
        if (response.isSuccessful()) {
            adapter.clear();
            adapter.addAll(products.getProducts());
            Log.i(TAG, "Products: ");
        }
    }

    @Override
    public void onFailure(Call<ProductList> call, Throwable t) {

    }

    /* @Override
    public void onResponse(Call<ProductList> call, Response<ProductList> response) {
        //final List<Product> products = response.body();


        final ProductList products = response.body();
        if (response.isSuccessful()) {
            Log.i(TAG, "Products: " + products.size());
            adapter.clear();
            adapter.addAll(products);
        } else {
            String errorMessage;
            try {
                errorMessage = "Napaka: " + response.errorBody().string();
            } catch (IOException e) {
                errorMessage = "Napaka.";
            }
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            Log.e(TAG, errorMessage);
        }
        container.setRefreshing(false);
    }

    @Override
    public void onFailure(Call<List<Product>> call, Throwable t) {
        Log.w(TAG, "Error: " + t.getMessage(), t);
        container.setRefreshing(false);
    }*/
}

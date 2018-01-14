package com.example.miha.ep_trgovina_android;

import android.content.Intent;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataActivity extends AppCompatActivity implements Callback<UserWrapper> {
    private static final String TAG = "UserDataActivity";

    private TextView ime;
    private TextView priimek;
    private TextView email;
    private TextView naslov;
    private TextView tel_stevilka;

    private Button posodobi;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        ime = (TextView) findViewById(R.id.ime);
        priimek = (TextView) findViewById(R.id.priimek);
        email = (TextView) findViewById(R.id.email);
        naslov = (TextView) findViewById(R.id.naslov);
        tel_stevilka = (TextView) findViewById(R.id.tel_stevilka);

        final MyApplicationObject app = (MyApplicationObject) getApplication();
        if (app.user != null) {
            ime.setText(app.user.getIme());
            priimek.setText(app.user.getPriimek());
            email.setText(app.user.getEmail());
            naslov.setText(app.user.getNaslov());
            tel_stevilka.setText(app.user.getTel_stevilka());
        }

        posodobi = (Button) findViewById(R.id.posodobi);
        posodobi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currIme = ime.getText().toString();
                String currPriimek = priimek.getText().toString();
                String currEmail = email.getText().toString();
                String currNaslov = naslov.getText().toString();
                String currTel_stevilka = tel_stevilka.getText().toString();
                ProductService.getInstance().update(app.user.sessionCookie, currIme, currPriimek, currEmail, currNaslov, currTel_stevilka).enqueue(UserDataActivity.this);
                startActivity(new Intent(UserDataActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
        final Headers headers = response.headers();
        Log.i(TAG, "Status:" + response.code());
        if (response.isSuccessful()) {

            user = response.body().getUser();

            MyApplicationObject app = (MyApplicationObject) getApplication();
            String cookie = app.user.sessionCookie;
            app.user = user;
            app.user.sessionCookie = cookie;

            Log.i(TAG, "Editing saved.");

        }
    }

    @Override
    public void onFailure(Call<UserWrapper> call, Throwable t) {
        Log.w(TAG, "Error: " + t.getMessage(), t);
    }
}

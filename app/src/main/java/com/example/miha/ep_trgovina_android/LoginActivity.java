package com.example.miha.ep_trgovina_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Callback<UserWrapper> {

    private static final String TAG = "LoginActivity";

    private EditText email, geslo;
    private Button buttonSave, buttonHelp;

    MyApplicationObject app;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        geslo = (EditText) findViewById(R.id.geslo);
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonHelp = (Button) findViewById(R.id.button_help);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mojEmail = email.getText().toString();
                String mojeGeslo = geslo.getText().toString();
                System.out.println("api klic izveden");
                ProductService.getInstance().login(mojEmail, mojeGeslo).enqueue(new Callback<UserWrapper>() {
                    @Override
                    public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                        user = response.body().getUser();
                        Log.i(TAG, "Got result: " + user);
                        app = (MyApplicationObject) getApplication();
                        app.user = user;
                        goToHelpActivity();
                    }

                    @Override
                    public void onFailure(Call<UserWrapper> call, Throwable t) {
                        Log.i(TAG, "ERROR" + t.getMessage(), t);
                    }
                });

                //saveToAppObject();
            }
        });

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHelpActivity();
            }
        });
    }

    private void goToHelpActivity() {
        startActivity(new Intent(this, UserDataActivity.class));
    }

   /* public void saveToAppObject() {
        final MyApplicationObject app = (MyApplicationObject) getApplication();
       // app.ime = name.getText().toString();
       // name.setText("");
    }*/

    @Override
    public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {

    }

    @Override
    public void onFailure(Call<UserWrapper> call, Throwable t) {

    }
}

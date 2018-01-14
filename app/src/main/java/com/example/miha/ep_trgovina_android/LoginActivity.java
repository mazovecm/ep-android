package com.example.miha.ep_trgovina_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
    private Button buttonLogin, buttonData, buttonLogout;

    public MyApplicationObject app;
    public User user;
    public boolean err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        geslo = (EditText) findViewById(R.id.geslo);
        buttonLogin = (Button) findViewById(R.id.button_save);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mojEmail = email.getText().toString();
                String mojeGeslo = geslo.getText().toString();
                System.out.println("api klic izveden");

                if (!mojEmail.isEmpty() && !mojeGeslo.isEmpty()) {
                    ProductService.getInstance().login(mojEmail, mojeGeslo).enqueue(new Callback<UserWrapper>() {
                        @Override
                        public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                            err = response.body().isError();
                            if (!err) {
                                user = response.body().getUser();
                                user.sessionCookie = response.headers().get("Set-Cookie");
                                Log.i(TAG, "Got result: " + user);
                                app = (MyApplicationObject) getApplication();
                                app.user = user;
                                goToMainActivity();
                            } else openDialog2();
                        }

                        @Override
                        public void onFailure(Call<UserWrapper> call, Throwable t) {
                            Log.i(TAG, "ERROR" + t.getMessage(), t);
                        }
                    });
                }
                else openDialog3();
            }
        });
    }

    private void goToMainActivity() {
        final MyApplicationObject app = (MyApplicationObject) getApplication();
        if (app.user != null)
            startActivity(new Intent(this, MainActivity.class));
        else
            openDialog();
    }

    private void openDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage("Najprej se prijavi.");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        alertDialog.show();
    }
    private void openDialog2() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage("Napačen E-mail in/ali geslo.");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        alertDialog.show();
    }
    private void openDialog3() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage("Vnesti moraš E-mail in geslo.");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        alertDialog.show();
    }

    @Override
    public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {

    }

    @Override
    public void onFailure(Call<UserWrapper> call, Throwable t) {

    }
}

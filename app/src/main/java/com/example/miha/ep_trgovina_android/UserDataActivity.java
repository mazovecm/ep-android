package com.example.miha.ep_trgovina_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDataActivity extends AppCompatActivity {
    private static final String TAG = "UserDataActivity";

    private TextView ime;
    private TextView priimek;
    private TextView email;
    private TextView naslov;
    private TextView tel_stevilka;
    //private TextView geslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        ime = (TextView) findViewById(R.id.ime);
        priimek = (TextView) findViewById(R.id.priimek);
        email = (TextView) findViewById(R.id.email);
        naslov = (TextView) findViewById(R.id.naslov);
        tel_stevilka = (TextView) findViewById(R.id.tel_stevilka);
        //geslo = (TextView) findViewById(R.id.geslo);

        final MyApplicationObject app = (MyApplicationObject) getApplication();
        if (app.user != null) {
            ime.setText(app.user.getIme());
            priimek.setText(app.user.getPriimek());
            email.setText(app.user.getEmail());
            naslov.setText(app.user.getNaslov());
            tel_stevilka.setText(app.user.getTel_stevilka());
            //geslo.setText(app.user.getGeslo());
        }
    }
}

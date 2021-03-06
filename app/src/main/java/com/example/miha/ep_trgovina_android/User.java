package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Miha on 14. 01. 2018.
 */

public class User {

    @SerializedName("id_uporabnik")
    @Expose
    public int id_uporabnik;
    @SerializedName("ime")
    @Expose
    public String ime;
    @SerializedName("priimek")
    @Expose
    public String priimek;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("naslov")
    @Expose
    public String naslov;
    @SerializedName("tel_stevilka")
    @Expose
    public String tel_stevilka;
    @SerializedName("geslo")
    @Expose
    public String geslo;


    public String sessionCookie;



    @Override
    public String toString() {
        return String.format("Ime: %s\nPriimek: %s\nEmail: %s\nNaslov: %s\nTel. stevilka: %s\n",
                                ime, priimek, email, naslov, tel_stevilka);
    }

    public int getId_uporabnik() {
        return id_uporabnik;
    }

    public void setId_uporabnik(int id_uporabnik) {
        this.id_uporabnik = id_uporabnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTel_stevilka() {
        return tel_stevilka;
    }

    public void setTel_stevilka(String tel_stevilka) {
        this.tel_stevilka = tel_stevilka;
    }

    public String getGeslo() {
        return geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }
}

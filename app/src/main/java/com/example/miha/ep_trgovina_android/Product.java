package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Miha on 8. 01. 2018.
 */

public class Product implements Serializable {

    public int id;
    public String naziv, opis;
    public double cena, ocena;

    public List<ProductImages> slike;

    @Override
    public String toString() {
        return String.format("Id: %d\nNaziv: %s\nOpis: %s\nCena: %.2f\nOcena: %.2f\n",
                                    id, naziv, opis, cena, ocena);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }
}

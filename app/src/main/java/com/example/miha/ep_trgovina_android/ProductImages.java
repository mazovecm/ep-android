package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Miha on 10. 01. 2018.
 */

public class ProductImages implements Serializable {

    private int id_slike;
    private int id_produkt;
    private String pot;

    @Override
    public String toString() {
        return String.format("ID SLIKE : %d\nID PRODUKT: %d\nPOT: %s", id_slike, id_produkt, pot);
    }

    public int getId_slike() {
        return id_slike;
    }

    public void setId_slike(int id_slike) {
        this.id_slike = id_slike;
    }

    public int getId_produkt() {
        return id_produkt;
    }

    public void setId_produkt(int id_produkt) {
        this.id_produkt = id_produkt;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }
}

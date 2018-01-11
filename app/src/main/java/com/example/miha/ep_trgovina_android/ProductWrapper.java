package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Miha on 10. 01. 2018.
 */

public class ProductWrapper implements Serializable{

    @SerializedName("data")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

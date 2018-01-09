package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Miha on 9. 01. 2018.
 */

public class ProductList implements Serializable {

    @SerializedName("data")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Miha on 14. 01. 2018.
 */

public class UserWrapper implements Serializable {

    @SerializedName("uporabnik")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

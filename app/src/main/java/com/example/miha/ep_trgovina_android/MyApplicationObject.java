package com.example.miha.ep_trgovina_android;

import android.app.Application;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Miha on 14. 01. 2018.
 */

public class MyApplicationObject extends Application {


    public User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

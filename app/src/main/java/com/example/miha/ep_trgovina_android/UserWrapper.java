package com.example.miha.ep_trgovina_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Miha on 14. 01. 2018.
 */

public class UserWrapper implements Serializable {

    @SerializedName("error")
    @Expose
    public boolean error;
    @SerializedName("uporabnik")
    @Expose
    public User user;

    public User getUser() {
        return user;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

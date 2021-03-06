package com.coreproc.android.kitchen.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IanBlanco on 10/27/2016.
 */

public class LoginCredentials {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

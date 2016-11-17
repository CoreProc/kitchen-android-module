package com.coreproc.android.kitchen.responses;

import com.coreproc.android.kitchen.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kael on 11/16/2016.
 */

public class UserData implements Serializable {

    @SerializedName("data")
    private User user;

    public User getUser() {
        return user;
    }
}

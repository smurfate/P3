package com.itland.employer.abstracts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Saad on 5/9/2018.
 */

public abstract class AbstractEntity {
    public boolean IsOk;
    public com.itland.employer.entities.Message Message;

    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static <T> T fromJson(String json)
    {
        Gson gson = new Gson();
        Type type = new TypeToken<T>(){}.getType();
        return gson.fromJson(json, type);
    }
}

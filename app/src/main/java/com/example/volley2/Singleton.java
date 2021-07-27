package com.example.volley2;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton instance;
    RequestQueue requestQueue;


    public Singleton(Context context) {

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }
    public static synchronized Singleton getInstance(Context context){
    if(instance==null)
        instance = new Singleton(context);
    return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}

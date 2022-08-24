package com.example.wallpaperapplication;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingelton {
    private RequestQueue requestQueue;
    private static VolleySingelton VInstance;
    private VolleySingelton(Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingelton getVInstance(Context context){
        if (VInstance==null){
            VInstance=new VolleySingelton(context);
        }
        return VInstance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;


    }
}

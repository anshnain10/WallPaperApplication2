package com.example.wallpaperapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPref {
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public void setPref(Context context, String key, String value){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPref(Context context, String key){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        String value = null;
        String X = sp.getString(key, value);
        return X;
    }
}

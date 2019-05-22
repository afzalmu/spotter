package com.afzalmu.spotter.spotter;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    public static void setUsername(String value) {
        preferenceSetter("getUsername", value);
    }

    public static String getUsername() {
        return preferenceGetter("getUsername");
    }


    public static void setLat(String value) {
        preferenceSetter("lat", value);
    }

    public static String getLat() {
        return preferenceGetter("lat");
    }


    public static void setLon(String value) {
        preferenceSetter("lon", value);
    }

    public static String getLon() {
        return preferenceGetter("lon");
    }




    public static void setIsLoggedIn(String value) {
        preferenceSetter("setIsLoggedIn", value);
    }

    public static String getIsLoggedIn() {
        return preferenceGetter("setIsLoggedIn");
    }

    public static void setPhone(String value) {
        preferenceSetter("phone", value);
    }

    public static String getPhone() {
        return preferenceGetter("phone");
    }


    public static void preferenceSetter(String key, String value) {
        SharedPreferences pref = ApplicationClass.getInstance().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String preferenceGetter(String key) {
        SharedPreferences pref;
        String value = "";
        pref = ApplicationClass.getInstance().getSharedPreferences("user", Context.MODE_PRIVATE);
        value = pref.getString(key, "");
        return value;
    }


}

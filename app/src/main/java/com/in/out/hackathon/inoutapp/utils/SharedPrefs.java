package com.in.out.hackathon.inoutapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vrihas on 21/5/2019.
 */
public class SharedPrefs {

    // Shared preferences file name
    private static final String PREF_NAME = "FitzuSharedPreferences";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_EMAIL = "email";

    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONIGTUDE = "longitude";
    public static final String KEY_NEARBY_PARKING = "nearbyParking";
    public static final String KEY_USER_ID = "userId";

    private static final int KEY_VERSION=1;
    // LogCat tag
    private static String TAG = "Shared Preference";
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }


    public String getMobile() {
        return pref.getString(KEY_MOBILE, "0");
    }

    public void setMobile(String mobile) {
        editor.putString(KEY_MOBILE, mobile);
        editor.commit();
    }


    public int getUserId() {
        return pref.getInt(KEY_USER_ID, -1);
    }

    public void setUserId(int userId) {
        editor.putInt(KEY_USER_ID, userId);
        editor.commit();
    }



    public void setLatitude(float latitude) {
        editor.putFloat(KEY_LATITUDE, latitude);
        editor.commit();
    }

    public float getLatitude() {
        return pref.getFloat(KEY_LATITUDE, 0);
    }

    public void setLongitude(float longitude) {
        editor.putFloat(KEY_LONIGTUDE, longitude);
        editor.commit();
    }

    public float getLongitude() {
        return pref.getFloat(KEY_LONIGTUDE, 0);
    }

    public String getNearbyParking() {
        return pref.getString(KEY_NEARBY_PARKING, "default");
    }

    public void setNearbyParking(String jsonFormat) {
        editor.putString(KEY_NEARBY_PARKING, jsonFormat);
        editor.commit();
    }
}

package com.cyq.mvshow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * android application's default global settings (collection of string key-value pairs).
 */
public class Settings {
    private static final String TAG = "Settings";

    private static final String SHARE_NAME = "Settings";

    private final Context mContext;
    // the SharedPreferences name (actually it's the underlying xml file name, under /data/data/[package]/shared_prefs/)
    private final String mName;
    
    private static Settings setting = null;

    /**
     * construct application settings with specified name.
     * @param ctx
     */
    public Settings(Context ctx, String name) {
        mContext = ctx;
        mName = name;
    }

    /**
     * construct the default application settings (Settings.xml).
     * @param context
     */
    public Settings(Context context) {
        this.mContext = context;
        mName = SHARE_NAME;
    }
    
    public static Settings getInstance(Context context){
    	if(setting == null){
    		setting = new Settings(context); 
    	}
    	return setting;
    }

    public void set(String key, String value) {
        SharedPreferences prefs = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        if (prefs == null) {
            return;
        }

        String getVal = prefs.getString(key, "");
        if (prefs.contains(key) && getVal.equals(value))
            return;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void remove(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        if (prefs == null) {
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * get key-value pair's value, return empty string if not found (or error)
     * @param key
     * @return
     */
    public String get(String key) {
        return get(key, "");
    }

    public String get(String key, String defVal) {
        SharedPreferences prefs = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        if (prefs == null) {
            return defVal;
        }

        String value = prefs.getString(key, defVal);
        return value;
    }

    // convenient methods

    /**
     * get key-value pair's value as boolean, if not exists or invalid, return false
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * get key-value pair's value as boolean, if not exists or invalid, return specified default value
     */
    public boolean getBoolean(String key, boolean defVal) {
        String value = get(key, null);

        if (value == null)
            return defVal;

        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            return defVal;
        }
    }

    /**
     * set boolean key-value pair
     */
    public void setBoolean(String key, boolean value) {
        set(key, Boolean.toString(value));
    }
    
    /**
     * get key-value pair's value as integer, if not exists or invalid, return 0
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * get key-value pair's value as integer, if not exists or invalid, return specified default value
     */
    public int getInt(String key, int defVal) {
        String value = get(key, null);
        
        if (value == null)
            return defVal;

        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return defVal;
        }
    }
    
    /**
     * set integer key-value pair
     */
    public void setInt(String key, int value) {
        set(key, Integer.toString(value));
    }

    /**
     * get key-value pair's value as long, if not exists or invalid, return specified default value
     */
    public long getLong(String key, long defVal) {
        String value = get(key);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defVal;
        }
    }
    
    public void setLong(String key, long value) {
        set(key, Long.toString(value));
    }

    /**
     * check whether contains the key in Settings.
     */
    public boolean contains(String key) {
        SharedPreferences prefs = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
        return (prefs != null && prefs.contains(key));
    }
}

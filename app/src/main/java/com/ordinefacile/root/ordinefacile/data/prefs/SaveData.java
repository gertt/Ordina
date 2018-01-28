package com.ordinefacile.root.ordinefacile.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 1/22/18.
 */

public class SaveData {
    Context ctx;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public SaveData(Context ctx1){
        this.ctx = ctx1;
        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        editor = preferences.edit();
    }

    public void saveLanguage(String language){
        editor.putString("language", language);
        editor.commit();
    }


    public String getLanguage(){
        return preferences.getString("language", "");

    }
}



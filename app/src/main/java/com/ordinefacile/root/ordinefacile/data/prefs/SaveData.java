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

    public void saveNumberCall(String number_call){
        editor.putString("number_call", number_call);
        editor.commit();
    }

    public void saveEntity(String entity_id){
        editor.putString("entity_id", entity_id);
        editor.commit();
    }

    public String getLanguage(){
        return preferences.getString("language", "");

    }

    public String getNumberCall(){
        return preferences.getString("number_call", "");

    }

    public String getEntity(){
        return preferences.getString("entity_id", "");

    }

    public void saveTokenFcm(String refreshedToken) {
        editor.putString("save_token", refreshedToken);
        editor.commit();
    }

    public String getTokenFcm(){
        return preferences.getString("save_token", "");

    }

    public void saveDeliveryStatus(String deliverystatus) {
        editor.putString("delivery_status", deliverystatus);
        editor.commit();
    }

    public String getDeliveryStatus(){
        return preferences.getString("delivery_status", "");

    }

    public void saveNumberCharacter(String number_character) {
        editor.putString("number_character", number_character);
        editor.commit();
    }

    public String getNumberCharacter(){
        return preferences.getString("number_character", "");

    }
}



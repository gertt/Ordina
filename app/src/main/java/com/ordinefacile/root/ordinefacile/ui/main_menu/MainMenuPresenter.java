package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.content.Context;

import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;

/**
 * Created by root on 1/22/18.
 */

public class MainMenuPresenter {

    MainMenuActivity mainMenuActivity;
    Context context;
    SaveData saveData;

    public MainMenuPresenter(MainMenuActivity mainMenuActivity,  Context context) {
        this.mainMenuActivity = mainMenuActivity;
        this.context = context;
        saveData = new SaveData(context);

    }

    public void getStoredId() {
        mainMenuActivity.getStoreId();
    }

    public void goToMenu(String id) {
        if (id!=null || !id.equalsIgnoreCase("")){
            mainMenuActivity.goToMenu();
        }
    }
    public void checknumber() {
        if (!saveData.getNumberCall().equalsIgnoreCase("")){

            mainMenuActivity.callNumber(saveData.getNumberCall());
        }else {
            mainMenuActivity.callNumberIncorrect();
        }
    }
}

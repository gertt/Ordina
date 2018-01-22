package com.ordinefacile.root.ordinefacile.ui.main_menu;

/**
 * Created by root on 1/22/18.
 */

public class MainMenuPresenter {

    MainMenuActivity mainMenuActivity;

    public MainMenuPresenter(MainMenuActivity mainMenuActivity) {
        this.mainMenuActivity = mainMenuActivity;
    }


    public void getStoredId() {
        mainMenuActivity.getStoreId();
    }

    public void goToMenu(String id) {
        if (id!=null || !id.equalsIgnoreCase("")){
            mainMenuActivity.goToMenu();
        }
    }
}

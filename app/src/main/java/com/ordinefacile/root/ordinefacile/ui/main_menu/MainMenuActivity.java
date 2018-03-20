package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.menu.MenuActivity;

public class MainMenuActivity extends AppCompatActivity implements  MainMenuView {

    Button button_menu ;
    Button button_call_service ;
    String id;
    MainMenuPresenter mainMenuPresenter;
    SaveData saveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        saveData = new SaveData(getApplicationContext());

        mainMenuPresenter = new MainMenuPresenter(this,getApplicationContext());

        button_menu = (Button)findViewById(R.id.button_menu);
        button_call_service = (Button)findViewById(R.id.button_call_service);

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainMenuPresenter.getStoredId();

            }
        });

        button_call_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainMenuPresenter.checknumber();

            }
        });
    }

    @Override
    public void getStoreId() {
        id = getIntent().getExtras().getString("storeId","");
        mainMenuPresenter.goToMenu(id);
    }

    @Override
    public void goToMenu() {
        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
        i.putExtra("storeId",id);
        startActivity(i);
        //finish();

    }
    @Override
    public void callNumber(String numberCall) {

        Intent call = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numberCall, null));
        startActivity(call);

    }
    @Override
    public void callNumberIncorrect() {

        Toast.makeText(getApplicationContext(),R.string.numberIncorrect,Toast.LENGTH_LONG).show();
    }
}

package com.ordinefacile.root.ordinefacile.ui.mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.ui.menu.MenuActivity;
import com.ordinefacile.root.ordinefacile.ui.menu.MenuPresenter;

import butterknife.BindView;

public class MainMenuActivity extends AppCompatActivity implements  MainMenuView {

    Button button_menu ;
    Button button_call_service ;
    String id;
    MainMenuPresenter mainMenuPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainMenuPresenter = new MainMenuPresenter(this);


        button_menu = (Button)findViewById(R.id.button_menu);
        button_call_service = (Button)findViewById(R.id.button_call_service);

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainMenuPresenter.getStoredId();

                Toast.makeText(getApplicationContext(),"menu",Toast.LENGTH_LONG).show();

            }
        });

        button_call_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"SERVICE",Toast.LENGTH_LONG).show();


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
        finish();

    }
}

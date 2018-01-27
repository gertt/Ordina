package com.ordinefacile.root.ordinefacile.ui.select_language;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.ui.help.HelpActivity;
import com.ordinefacile.root.ordinefacile.ui.scan.ScannerActivity;
import com.ordinefacile.root.ordinefacile.utils.LocaleHelper;

import java.util.Locale;

public class SelectLanguageActivity extends AppCompatActivity  implements  SelectLanguageView{

    private RadioButton button_en;
    private  RadioButton button_it;
    Locale mylocale;

    SelectLanguagePersenter selectLanguagePersenter;

    Button buttonBackSelectLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        button_en = (RadioButton) findViewById(R.id.radioButton_en);
        button_it = (RadioButton)findViewById(R.id.radioButton_it);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.tittlebar_select_language);


        buttonBackSelectLanguage = (Button)findViewById(R.id.button_back_selectlanguage);
        buttonBackSelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        selectLanguagePersenter = new SelectLanguagePersenter(this,getApplicationContext());

        selectLanguagePersenter.checkRadioButton();


        button_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(getApplicationContext(), "en");
                Intent refreshIntent=new Intent(SelectLanguageActivity.this,ScannerActivity.class);
               startActivity(refreshIntent);

               // Snackbar.make(v,"Language is changed ", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();

            }
        });

        button_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocaleHelper.setLocale(getApplicationContext(), "it");
                Intent refreshIntent=new Intent(SelectLanguageActivity.this,ScannerActivity.class);
                startActivity(refreshIntent);
              //  Snackbar.make(v,"Language is changed ", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();

            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setButtonEnChecket() {
        button_en.setChecked(true);
        button_it.setChecked(false);
    }

    @Override
    public void setButtonEnUnChecket() {
        button_en.setChecked(false);
        button_it.setChecked(true);

    }
}

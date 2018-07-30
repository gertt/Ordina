package com.ordinefacile.root.ordinefacile.ui.select_language;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.code_scan.CodeOrScanActivity;
import com.ordinefacile.root.ordinefacile.utils.LocaleManager;

import static com.ordinefacile.root.ordinefacile.utils.LocaleManager.LANGUAGE_ENGLISH;
import static com.ordinefacile.root.ordinefacile.utils.LocaleManager.LANGUAGE_ITALIAN;

public class SelectLanguageActivity extends AppCompatActivity  implements  SelectLanguageView{

    private RadioButton button_en;
    private  RadioButton button_it;
    SaveData saveData;

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
        saveData = new SaveData(this);

        String x = DebugDB.getAddressLog();


        //Open http://192.168.1.3:8080 in your browser

        //Open http://192.168.100.35:8080 in your browser  titi

        //Open http://192.168.100.58:8080 in your browser


        //Open http://192.168.100.58:8080 in your browser
        //Open http://192.168.100.58:8080 in your browser

        //Open http://192.168.100.58:8080 in your browser
        //Open http://192.168.100.58:8080 in your browser

       //Open http://192.168.1.3:8080 in your browser

        //MyJobServiceCk

        Boolean boolean1 = Boolean.valueOf("true");
        boolean boolean2 = Boolean.parseBoolean("true");

        System.out.print("bolean1"+boolean1);
        System.out.print(boolean1);
        System.out.print(boolean2);
        System.out.print(boolean2);

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

                setNewLocale(LANGUAGE_ENGLISH, false);

                saveData.saveLanguage("en");
                Intent refreshIntent=new Intent(SelectLanguageActivity.this,CodeOrScanActivity.class);
                startActivity(refreshIntent);
            }
        });

        button_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNewLocale(LANGUAGE_ITALIAN, false);

                saveData.saveLanguage("it");
                Intent refreshIntent=new Intent(SelectLanguageActivity.this,CodeOrScanActivity.class);
                startActivity(refreshIntent);
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
        button_en.setChecked(false);
        button_it.setChecked(false);
    }

    @Override
    public void setButtonEnUnChecket() {
        button_en.setChecked(false);
        button_it.setChecked(false);

    }

    private boolean setNewLocale(String language, boolean restartProcess) {
        LocaleManager.setNewLocale(this, language);

        Intent i = new Intent(this, SelectLanguageActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        if (restartProcess) {
            System.exit(0);
        } else {
            Toast.makeText(this, "Activity restarted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}

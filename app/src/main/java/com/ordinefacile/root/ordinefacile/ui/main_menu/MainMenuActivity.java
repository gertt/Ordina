package com.ordinefacile.root.ordinefacile.ui.main_menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.code_scan.CodeOrScanActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_category.MenuActivity;
import com.ordinefacile.root.ordinefacile.ui.push_history.PushHistoryActivity;


public class MainMenuActivity extends AppCompatActivity implements  MainMenuView {

    Button button_menu;
    Button button_call_service;
    String id;
    MainMenuPresenter mainMenuPresenter;
    SaveData saveData;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_menu);

        saveData = new SaveData(getApplicationContext());
        mainMenuPresenter = new MainMenuPresenter(this, getApplicationContext());

        button_menu = (Button) findViewById(R.id.button_menu);
        button_call_service = (Button) findViewById(R.id.button_call_service);

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainMenuPresenter.getStoredId();

            }
        });

        button_call_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMaterialDialog();

            }
        });
    }

    @Override
    public void getStoreId() {
        id = saveData.getStoreId();
        mainMenuPresenter.goToMenu(id);
    }

    @Override
    public void goToMenu() {
        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
        i.putExtra("storeId", id);
        startActivity(i);

    }

    @Override
    public void showSendingSms() {

        alertDialog.dismiss();

       Toast.makeText(getApplicationContext(), R.string.send_sms_succees, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showErrorSending(String s) {

        alertDialog.dismiss();

        Toast.makeText(getApplicationContext(), R.string.send_sms_error, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed()
    {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {

            showMaterialDialogLogout();
        }

        if (id == R.id.action_push) {
            Intent intent = new Intent(getApplicationContext(), PushHistoryActivity.class);
            startActivity(intent);
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    private void showMaterialDialog() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_send, null);
        dialogBuilder.setView(dialogView);

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final Button btn_insert = (Button) dialogView.findViewById(R.id.button_call_service);
        final Button btn_cancel = (Button) dialogView.findViewById(R.id.button_cancel);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mainMenuPresenter.callService();

            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }


    private void showMaterialDialogLogout() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_logout, null);
        dialogBuilder.setView(dialogView);

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final Button btn_insert = (Button) dialogView.findViewById(R.id.button_insert);
        final Button btn_cancel = (Button) dialogView.findViewById(R.id.button_cancel);

        //   final TextView txt_pin = (TextView) dialogView.findViewById(R.id.textView_insert_pin);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            saveData.ClearAll();
            Intent intent = new Intent(getApplicationContext(), CodeOrScanActivity.class);
            startActivity(intent);


            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
}
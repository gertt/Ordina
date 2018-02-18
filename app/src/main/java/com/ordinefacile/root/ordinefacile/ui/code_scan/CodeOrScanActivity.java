package com.ordinefacile.root.ordinefacile.ui.code_scan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.ui.main_menu.MainMenuActivity;
import com.ordinefacile.root.ordinefacile.ui.scan.ScannerActivity;


public class CodeOrScanActivity extends AppCompatActivity implements  CodeOrScanView{



    private Button btn_scan;
    private Button btn_pin;
    private  AlertDialog alertDialog;

    CodeOrScanPresenter codeOrScanPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_or_scan);

        codeOrScanPresenter = new CodeOrScanPresenter(getApplicationContext(),this);


        btn_scan = (Button)findViewById(R.id.button_scan_qr);
        btn_pin = (Button)findViewById(R.id.button_isert_pin);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScannerActivity.class);
                startActivity(intent);

            }
        });

        btn_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMaterialDialog();

            //    dialogInsertPin.feedback();

            }
        });


    }

    private void showMaterialDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_insertpin, null);
        dialogBuilder.setView(dialogView);

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final Button btn_insert = (Button) dialogView.findViewById(R.id.button_insert);
        final Button btn_cancel = (Button) dialogView.findViewById(R.id.button_cancel);
        final EditText edt_pin = (EditText) dialogView.findViewById(R.id.editText_inser_pin);
        final TextView txt_pin = (TextView) dialogView.findViewById(R.id.textView_insert_pin);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pin = edt_pin.getText().toString();

                codeOrScanPresenter.getStoreDetailByPin(pin);

            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void pinInvalid() {
        Toast.makeText(getApplicationContext(),R.string.pin_not_valid,Toast.LENGTH_LONG).show();

    }

    @Override
    public void goToMenuAtivity(String id) {
        alertDialog.dismiss();
        Intent i = new Intent(this, MainMenuActivity.class);
        i.putExtra("storeId",id);
        startActivity(i);


    }
}

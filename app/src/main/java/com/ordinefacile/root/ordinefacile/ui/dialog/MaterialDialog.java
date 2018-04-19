package com.ordinefacile.root.ordinefacile.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.ordinefacile.root.ordinefacile.R;

public class MaterialDialog extends AppCompatActivity {

    static Dialog dialog;
    TextView txt_ok;
    TextView nameTxtt;
    TextView contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void createDialog(Context context,String productName,String productPrice) {
        dialog = new Dialog(context);
        dialog.setTitle(R.string.info);
        dialog.setContentView(R.layout.layout_dialog);

        txt_ok = dialog.findViewById(R.id.txt_ok);

        txt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        nameTxtt = dialog.findViewById(R.id.nameTxtt);
        nameTxtt.setText("Product name : "+productName);
        contentTxt = dialog.findViewById(R.id.contentTxt);
        contentTxt.setText("Product price  : "+productPrice);

        dialog.show();

    }
}

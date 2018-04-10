package com.ordinefacile.root.ordinefacile.ui.add_product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;



public class AddProductActivity extends AppCompatActivity  implements  AddProductView {

    private String quantity;
    private String name;
    private String price;
    private String metric;
    private String description;
    private String urlImage;
    private String id_product;

    private TextView txt_quantity;
    private TextView txt_name;
    private TextView txt_price;
    private TextView txt_metric;
    private TextView txt_description;

    private View view4;

    private ImageView img_urlImage;

    private ParseImage parseImage;

    AddProductPresenter addProductPresenter;

    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parseImage = new ParseImage(getApplicationContext());
        saveData = new SaveData(getApplicationContext());
        addProductPresenter = new AddProductPresenter(this,getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu);

        Intent intent = getIntent();
        quantity = intent.getStringExtra("categoryDetailQuantity");
        name = intent.getStringExtra("categoryDetailName");
        price = intent.getStringExtra("categoryDetailPrice");
        metric = intent.getStringExtra("categoryDetailMetric");
        description = intent.getStringExtra("categoryDetailDescription");
        urlImage = intent.getStringExtra("categoryDetailUrlImg");




        id_product = intent.getStringExtra("categoryDetaiIdProduct");

        txt_quantity = (TextView)findViewById(R.id.textView_quantity);
        txt_name = (TextView)findViewById(R.id.textView_tittle);
        txt_price = (TextView)findViewById(R.id.textView_price);
        txt_metric = (TextView)findViewById(R.id.textView_metric);
        txt_description = (TextView)findViewById(R.id.textView_description);
        img_urlImage = (ImageView)findViewById(R.id.imageView_addproduct);
        view4 = (View)findViewById(R.id.view4);

        txt_quantity.setText(quantity);
        txt_name.setText(name);
        txt_price.setText(price);
        txt_metric.setText(metric);
        txt_description.setText(description);
        parseImage.parseimage(urlImage,img_urlImage);


        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float quantity_float =Float.parseFloat(quantity);
                Float quantity_price =Float.parseFloat(price);

                Float final_price =quantity_price * quantity_float;

                addProductPresenter.update(final_price,quantity_float, name,quantity_price,metric,description,urlImage,saveData.getEntity(),id_product);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call_service) {

            addProductPresenter.checknumber();
        }
        if (id == R.id.action_my_order) {
               Intent intent = new Intent(getApplicationContext(),MyOrderActivity.class);
               startActivity(intent);
        }
        if (id == R.id.action_history) {
            Intent intent = new Intent(getApplicationContext(),OrderHistoryActivity.class);
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

        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return super.onCreateOptionsMenu(menu);
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

    @Override
    public void goToMyOrder() {

        Intent intent = new Intent(getApplicationContext(), MyOrderActivity.class);
        startActivity(intent);
        finish();

    }
}

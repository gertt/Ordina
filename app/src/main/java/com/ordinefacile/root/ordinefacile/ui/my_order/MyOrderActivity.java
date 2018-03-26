package com.ordinefacile.root.ordinefacile.ui.my_order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.ui.code_scan.CodeOrScanActivity;
import com.ordinefacile.root.ordinefacile.ui.main_menu.MainMenuActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.ui.select_language.SelectLanguageActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements MyOrderView {

    private static final String TAG = "My Debug";
    private RecyclerView mRecyclerView;
    private MyOrderAdapter adapter;

    MyOrderPresenter myOrderPresenter;
    DatabaseHelper dbHelper;
    ParseImage parseImage;
    SlimAdapter slimAdapter;


    ArrayList<Orders> muylis = new ArrayList<Orders>();

    String jsoni;
    int id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_myorder);
        EventBus bus = EventBus.getDefault();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_myorder);
        dbHelper = new DatabaseHelper(getApplicationContext());

        myOrderPresenter = new MyOrderPresenter(getApplicationContext(),this);
        myOrderPresenter.getListProducts();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        slimAdapter = SlimAdapter.create();
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // jsoni = "{\"table_id\":\"1\",\"order_items\":[{\"mDescriptions\":\"  Aut quasi ex sit cor\",\"mFinalPrice\":36.22,\"mId\":50,\"mIdProduct\":\"213\",\"mIdTable\":\"213\",\"mMetric\":\"  g  \",\"mName\":\"  Ms. Veda Parker V  \",\"mPrice\":36.22,\"mQuantity\":1,\"mUrl_Image\":\"storage\\/images\\/products\\/e3ea51dd047185745f2d7fe86f70b0b1125068784.jpeg\"}],\"device\":{\"device_token\":\"tokeni jone\",\"brand\":\"samusng\",\"model\":\"smg900f\"}}";
                myOrderPresenter.sendJson();
            }
        });

    }

    @Override
    public void listAdapter(List<Orders> feedItemList) {


        muylis = (ArrayList<Orders>) feedItemList;
        adapter = new MyOrderAdapter(MyOrderActivity.this, feedItemList,this);
        mRecyclerView.setAdapter(adapter);
        System.out.println(feedItemList.size());
        Log.d(TAG,feedItemList.toString());
        adapter.notifyDataSetChanged();


    }

    @Override
    public void idProduct(int int_product) {


        myOrderPresenter.delete(int_product);
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
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(Eventlist event) {

   String sjsj = event.getEmri();
   String sjssj = event.getEmri();

    Toast.makeText(getApplicationContext(),"printoje",Toast.LENGTH_LONG).show();

          if (sjsj.equalsIgnoreCase("myorder_activity")){

             // Intent refreshIntent=new Intent(getApplicationContext(),MainMenuActivity.class);
            //  startActivity(refreshIntent);

          }


        myOrderPresenter.sendJson();

    }
}
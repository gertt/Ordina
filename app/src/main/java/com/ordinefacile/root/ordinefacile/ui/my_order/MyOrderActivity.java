package com.ordinefacile.root.ordinefacile.ui.my_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.ui.menu.MenuActivityAdapter;

import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements MyOrderView {

    private static final String TAG = "My Debug";
    private RecyclerView mRecyclerView;
    private MyOrderAdapter adapter;

    MyOrderPresenter myOrderPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_myorder);


        myOrderPresenter = new MyOrderPresenter(getApplicationContext(),this);
        myOrderPresenter.getListProducts();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_myorder);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

    }

    @Override
    public void listAdapter(List<Orders> feedItemList) {


        adapter = new MyOrderAdapter(getApplicationContext(), feedItemList);
        mRecyclerView.setAdapter(adapter);
        System.out.println(feedItemList.size());
        Log.d(TAG,feedItemList.toString());
        adapter.notifyDataSetChanged();

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
}

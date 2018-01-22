package com.ordinefacile.root.ordinefacile.ui.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategories;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategoriesData;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuView{

    MenuPresenter menuPresenter;
    String id;

    private RecyclerView mRecyclerView;
    private MenuActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuPresenter = new MenuPresenter(this);
        menuPresenter.getStoreId();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);
    }

    @Override
    public void getStoreId() {
      id = getIntent().getExtras().getString("storeId","");
      if(id != null || !id.equalsIgnoreCase("")){
          menuPresenter.getStoreCategories(id);
      }
    }

    @Override
    public void getListStoreCategories(List<StoreCategoriesData> feedItemList) {
        adapter = new MenuActivityAdapter(getApplicationContext(), feedItemList);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

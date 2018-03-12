package com.ordinefacile.root.ordinefacile.ui.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.amitshekhar.DebugDB;
import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;

import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuView{

    MenuPresenter menuPresenter;
    String id;

    private RecyclerView mRecyclerView;
    private MenuActivityAdapter adapter;
    PullRefreshLayout swipe_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);

        menuPresenter = new MenuPresenter(this,getApplicationContext());
        menuPresenter.getStoreId();

        String x = DebugDB.getAddressLog();

      //  Open http://192.168.1.4:8080 in your browser

       // Open http://192.168.100.58:8080 in your browser  db work s5
     //   menuPresenter.getListProducts();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

        swipe_menu = (PullRefreshLayout) findViewById(R.id.swipe_menu);
        swipe_menu.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 menuPresenter.getStoreCategories(id);
             }
         });
    }

    @Override
    public void getStoreId() {
      id = getIntent().getExtras().getString("storeId","");
      if(id != null || !id.equalsIgnoreCase("")){
          menuPresenter.getStoreCategories(id);

      }
    }

    @Override
    public void getListStoreCategories(List<CategoriesDataModel> feedItemList) {
        adapter = new MenuActivityAdapter(getApplicationContext(), feedItemList);
        mRecyclerView.setAdapter(adapter);
        System.out.println(feedItemList.size());
        adapter.notifyDataSetChanged();
        swipe_menu.setRefreshing(false);
    }

    @Override
    public void dissapearSwipeToRefresh() {
        swipe_menu.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {

        finish();
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

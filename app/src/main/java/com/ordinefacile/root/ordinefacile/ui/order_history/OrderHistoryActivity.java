package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailAdapter;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailPresenter;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderAdapter;

import java.util.List;


public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryView {


    SaveData saveData;

    OrderHistoryPresenter orderHistoryPresenter;
    String categoryId;
    MenuDetailPresenter menuDetailPresenter;

    private RecyclerView mRecyclerView;
    private OrderHistoryAdapter adapter;

   // PullRefreshLayout swipe_menu;
   FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.my_history);
         fm = getFragmentManager();

       orderHistoryPresenter = new OrderHistoryPresenter(getApplicationContext(),this);

       orderHistoryPresenter.getOrderHistory();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_myorder_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);


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
    public void listHistoryOrder(List<OrderHistoryData> feedItemList) {

        for (int i = 0;i<feedItemList.size();i++){

          String ejjd =  feedItemList.get(i).getStoreImage();

          String exjjd =  feedItemList.get(i).getStoreImage();

        }

        adapter = new OrderHistoryAdapter(getApplicationContext(), feedItemList,fm);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
      //  swipe_menu.setRefreshing(false);

    }
}

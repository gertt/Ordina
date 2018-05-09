package com.ordinefacile.root.ordinefacile.ui.push_history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistory;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailPresenter;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderAdapter;

import java.util.List;

public class PushHistoryActivity extends AppCompatActivity  implements  PushHistoryInterface{



    PushHistoryPresenter pushHistoryPresenter;
    PushHistoryAdapter adapter;

    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pushHistoryPresenter = new PushHistoryPresenter(this,this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_pushHistory);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

      //  pushHistoryPresenter.inserData("","","");

        pushHistoryPresenter.getListPush();


    }

    @Override
    public void listAdapter(List<PushHistory> feedItemList) {

        adapter = new PushHistoryAdapter(PushHistoryActivity.this, feedItemList, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

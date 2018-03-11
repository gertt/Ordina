package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.ui.help.HelpActivity;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistory;
import com.ordinefacile.root.ordinefacile.ui.select_language.SelectLanguageActivity;

import java.util.List;

public class MenuDetailActivity extends AppCompatActivity implements MenuDetailView{


    String categoryId;
    MenuDetailPresenter menuDetailPresenter;

    private RecyclerView mRecyclerView;
    private MenuDetailAdapter adapter;

    PullRefreshLayout swipe_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu);

        menuDetailPresenter = new MenuDetailPresenter(getApplicationContext(),this);

        menuDetailPresenter.getCategoryId();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

        swipe_menu = (PullRefreshLayout) findViewById(R.id.swipe_menu);
        swipe_menu.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menuDetailPresenter.getMenuDishes(categoryId);
            }
        });

    }

    @Override
    public void getMenuDetails() {
        categoryId = getIntent().getExtras().getString("categoryId","");
        if(categoryId != null || !categoryId.equalsIgnoreCase("")){
            menuDetailPresenter.getMenuDishes(categoryId);

            System.out.print("jsjs"+categoryId);
        }
    }

    @Override
    public void getListDished(List<MenuDishesDatum> feedItemList) {
        adapter = new MenuDetailAdapter(getApplicationContext(), feedItemList,menuDetailPresenter);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipe_menu.setRefreshing(false);
    }

    @Override
    public void dissapearSwipeToRefresh() {
        swipe_menu.setRefreshing(false);
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
    public void onBackPressed() {
        super.onBackPressed();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call_service) {

            menuDetailPresenter.checknumber();
        }
        if (id == R.id.action_my_order) {
          Intent intent = new Intent(getApplicationContext(),MyOrderActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_history) {
            Intent intent = new Intent(getApplicationContext(),OrderHistory.class);
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


}

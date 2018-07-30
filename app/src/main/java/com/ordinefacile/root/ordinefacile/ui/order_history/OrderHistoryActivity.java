package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.ui.main_menu.MainMenuActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_category.MenuActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailPresenter;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.utils.BaseActivity;

import java.util.List;
import static com.ordinefacile.root.ordinefacile.R2.color.myorder_yellow;


public class OrderHistoryActivity extends BaseActivity implements OrderHistoryView {

    OrderHistoryPresenter orderHistoryPresenter;
    String categoryId;
    MenuDetailPresenter menuDetailPresenter;



    private RecyclerView mRecyclerView;
    private OrderHistoryAdapter adapter;
    MaterialDialog dialog;
    PullRefreshLayout swipe_menu;
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

        dialog = new MaterialDialog.Builder(OrderHistoryActivity.this)
                .title(R.string.loading)
                .content(R.string.loading_history)
                .progress(true, 0)
                .cancelable(false)
                .widgetColorRes(myorder_yellow)
                .progressIndeterminateStyle(false)
                .show();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_myorder_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

        swipe_menu = (PullRefreshLayout) findViewById(R.id.swipe_myorder_history);

        swipe_menu.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderHistoryPresenter.getOrderHistory();
            }
        });

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

        adapter = new OrderHistoryAdapter(getApplicationContext(), feedItemList,fm);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipe_menu.setRefreshing(false);
        dialog.dismiss();

    }

    @Override
    public void errorLoading() {
        dialog.dismiss();
    }
    @Override
    public void onBackPressed() {
       super.onBackPressed();

      //  Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);
      //  startActivity(intent);

    }
}

package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.menu_category.MenuActivity;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity implements MenuDetailView{

    String categoryId;
    MenuDetailPresenter menuDetailPresenter;

    private RecyclerView mRecyclerView;
    private MenuDetailAdapter adapter;

    PullRefreshLayout swipe_menu;

    List<MenuDishesDatum> feedItemList2;

    FloatingActionButton fab;

    SaveData saveData;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu);

        saveData = new SaveData(getApplicationContext());

        menuDetailPresenter = new MenuDetailPresenter(getApplicationContext(),this);
        menuDetailPresenter.checkHideShowFloating();
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


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), MyOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void getMenuDetails() {
      //  categoryId = getIntent().getExtras().getString("categoryId","");
        categoryId = saveData.getDishesId();
        if(categoryId != null || !categoryId.equalsIgnoreCase("")){
            menuDetailPresenter.getMenuDishes(categoryId);


        }
    }

    @Override
    public void getListDished(List<MenuDishesDatum> feedItemList) {

        feedItemList2 = feedItemList ;
        adapter = new MenuDetailAdapter(getApplicationContext(), feedItemList2,menuDetailPresenter);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipe_menu.setRefreshing(false);

    }

    @Override
    public void dissapearSwipeToRefresh() {
        swipe_menu.setRefreshing(false);
    }


    @Override
    public void checkQuantity() {

        Toast.makeText(getApplicationContext(),R.string.check_quantity,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFloating() {
        fab.show();

    }

    @Override
    public void hideFloating() {

        fab.hide();
    }

    @Override
    public void showSendingSms() {

        alertDialog.dismiss();

        Toast.makeText(getApplicationContext(), R.string.send_sms_succees, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showErrorSending(String s) {

        alertDialog.dismiss();

        Toast.makeText(getApplicationContext(), R.string.send_sms_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_call_service) {

            showMaterialDialog();

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

    public void onRestart() {
        super.onRestart();
        menuDetailPresenter.checkHideShowFloating();
        adapter = new MenuDetailAdapter(getApplicationContext(), feedItemList2,menuDetailPresenter);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void showMaterialDialog() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_send, null);
        dialogBuilder.setView(dialogView);

        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.FILL_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        final Button btn_insert = (Button) dialogView.findViewById(R.id.button_call_service);
        final Button btn_cancel = (Button) dialogView.findViewById(R.id.button_cancel);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                menuDetailPresenter.callService();

            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

}


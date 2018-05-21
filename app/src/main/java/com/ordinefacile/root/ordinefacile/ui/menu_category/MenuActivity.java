package com.ordinefacile.root.ordinefacile.ui.menu_category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.main_menu.MainMenuActivity;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import com.ordinefacile.root.ordinefacile.ui.push_history.PushHistoryActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuView{

    MenuPresenter menuPresenter;
    PullRefreshLayout swipe_menu;
    ParseImage parseImage;
    RecyclerView mRecyclerView;
    SlimAdapter slimAdapter;
    String id;
    SaveData saveData;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_category);

        saveData = new SaveData(getApplicationContext());

        menuPresenter = new MenuPresenter(this,getApplicationContext());
        menuPresenter.getStoreId();

        parseImage = new ParseImage(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        mRecyclerView.setPadding(25, 25, 25, 25);

        slimAdapter = SlimAdapter.create();
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
        id = saveData.getStoreId();
        if(id != null || !id.equalsIgnoreCase("")){
            menuPresenter.getStoreCategories(id);

        }
    }
    @Override
    public void getListStoreCategories(List<CategoriesDataModel> feedItemList) {


        slimAdapter.register(R.layout.menu_adapter, new SlimInjector<CategoriesDataModel>() {
            @Override
            public void onInject(final CategoriesDataModel data, IViewInjector injector) {

                injector.with(R.id.imageView, new IViewInjector.Action<ImageView>() {
                    @Override
                    public void action(ImageView view) {

                        System.out.print("imazhijone :"+data.getImage());

                        parseImage.parseimage( data.getImage(),view);

                    }
                })
                        .clicked(R.id.imageView, new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {

                                saveData.saveDishesId(data.getId().toString());
                                Intent intent = new Intent(getApplicationContext(),MenuDetailActivity.class);
                                startActivity(intent);
                            }

                        });
            }
        })
                .attachTo(mRecyclerView)
                .updateData(feedItemList);

        swipe_menu.setRefreshing(false);
    }

    @Override
    public void dissapearSwipeToRefresh() {
        swipe_menu.setRefreshing(false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
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
        if (id == R.id.action_myorder) {
            Intent intent = new Intent(getApplicationContext(),MyOrderActivity.class);
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

        getMenuInflater().inflate(R.menu.menu_category, menu);

        return super.onCreateOptionsMenu(menu);
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


                menuPresenter.callService();

            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();

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



}
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

import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements MyOrderView {

    private static final String TAG = "My Debug";
    private RecyclerView mRecyclerView;
    private MyOrderAdapter adapter;

    MyOrderPresenter myOrderPresenter;
    DatabaseHelper dbHelper;
    ParseImage parseImage;
    SlimAdapter slimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.menu_myorder);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_myorder);
        dbHelper = new DatabaseHelper(getApplicationContext());

        myOrderPresenter = new MyOrderPresenter(getApplicationContext(),this);
        myOrderPresenter.getListProducts();

        parseImage = new ParseImage(getApplicationContext());


        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
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
           //  myOrderPresenter.sendMyorder();
            }
        });

    }

    @Override
    public void listAdapter(List<Orders> feedItemList) {

        slimAdapter.register(R.layout.menu_my_order_adapter, new SlimInjector<Orders>() {
            @Override
            public void onInject(final Orders data, IViewInjector injector) {

                injector.with(R.id.circularImageView_myorder, new IViewInjector.Action<ImageView>() {
                    @Override
                    public void action(ImageView view) {

                     //   for (int i = 0;i<data.getImages().size();i++){

                            String img_url = data.getmUrl_Image();
                            parseImage.parseimage(img_url,view);

                     //   }
                    }
                })

                        .with(R.id.imageView_myorder, new IViewInjector.Action<ImageView>() {
                    @Override
                    public void action(ImageView view) {


                        for (int i =0;i<feedItemList.size();i++){
                            final  Orders ordes1 = feedItemList.get(i);

                            if (ordes1.getmId()%2==0){

                                view.setBackgroundColor( Color.parseColor("#00D26A"));


                            }else if (ordes1.getmId()%2!=0){
                                view.setBackgroundColor( Color.parseColor("#F29C20"));


                            }



                        }

                    }
                });





            }


        })
                .attachTo(mRecyclerView)
                .updateData(feedItemList);


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

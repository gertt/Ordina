package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity implements MenuDetailView{


    String categoryId;
    MenuDetailPresenter menuDetailPresenter;

    private RecyclerView mRecyclerView;
    private MenuDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menuDetailPresenter = new MenuDetailPresenter(this);

        menuDetailPresenter.getCategoryId();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);

    }

    @Override
    public void getMenuDetails() {
        categoryId = getIntent().getExtras().getString("categoryId","");
        if(categoryId != null || !categoryId.equalsIgnoreCase("")){
            menuDetailPresenter.getMenuDishes(categoryId);
        }
    }

    @Override
    public void getListDished(List<MenuDishesDatum> feedItemList) {
        adapter = new MenuDetailAdapter(getApplicationContext(), feedItemList);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

package com.ordinefacile.root.ordinefacile.ui.menu_category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        id = getIntent().getExtras().getString("storeId","");
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

                                Intent intent = new Intent(getApplicationContext(),MenuDetailActivity.class);
                                intent.putExtra("categoryId", data.getId().toString()+"");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);

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
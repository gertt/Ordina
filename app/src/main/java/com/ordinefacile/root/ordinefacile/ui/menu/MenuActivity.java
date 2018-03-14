package com.ordinefacile.root.ordinefacile.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuView{

    MenuPresenter menuPresenter;
    String id;


   // private MenuActivityAdapter adapter;
    PullRefreshLayout swipe_menu;



  ParseImage parseImage;
    RecyclerView mRecyclerView;
    SlimAdapter slimAdapter;

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

        slimAdapter = SlimAdapter.create();


/*
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        mRecyclerView.setPadding(25, 25, 25, 25);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);
*/
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
                final CategoriesDataModel feedItem = feedItemList.get(position);

                injector.with(R.id.imageView, new IViewInjector.Action<ImageView>() {
                            @Override
                            public void action(ImageView view) {

                             //   parseImage.parseGlide(data.getImagePath(),getActivity(),view);
                                for (int i = 0;i<data.getImages().size();i++){

                                    String emri = data.getImages().get(i).getPath();
                                    String emsri = data.getImages().get(i).getPath();

                                    parseImage.parseimage(emsri,view);
                                }

                            }
                        })
                        .clicked(R.id.imageView, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                for (int i = 0;i<feedItemList.size();i++){


                                    Intent intent = new Intent(getApplicationContext(),MenuDetailActivity.class);
                                    intent.putExtra("categoryId", feedItemList.get(i).getId()+"");
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    getApplicationContext().startActivity(intent);


                                }
                            }
                        });

            }
        })
                  .attachTo(mRecyclerView)
                  .updateData(feedItemList);

        System.out.println(feedItemList.size());
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

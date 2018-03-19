package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.order_history.OrderHistoryActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.List;

import static com.ordinefacile.root.ordinefacile.R2.id.parent;

public class MenuDetailActivity extends AppCompatActivity implements MenuDetailView{


    String categoryId;
    MenuDetailPresenter menuDetailPresenter;

    private RecyclerView mRecyclerView;


    PullRefreshLayout swipe_menu;

    SlimAdapter slimAdapter;

    ParseImage parseImage;
    String img_url;

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

        parseImage = new ParseImage(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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


        slimAdapter.register(R.layout.menu_detail_adapter, new SlimInjector<MenuDishesDatum>() {
            @Override
            public void onInject(final MenuDishesDatum data, IViewInjector injector) {
                injector.text(R.id.textView5, data.getName())
                        .text(R.id.textView_price, data.getPrice())
                        .text(R.id.textView_metric, data.getMetrics())
                        .text(R.id.textView_description, data.getDescription());

                injector.with(R.id.imageView2, new IViewInjector.Action<ImageView>() {
                    @Override
                    public void action(ImageView view) {

                        for (int i = 0;i<data.getImages().size();i++){

                            img_url = data.getImages().get(i).getPath();
                            parseImage.parseimage(img_url,view);

                        }
                    }
                })
                        .clicked(R.id.view, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                menuDetailPresenter.checkQuantityOrGoActivity(
                                        "Add",
                                        data.getName().toString(),
                                        data.getPrice().toString(),
                                        data.getMetrics().toString(),
                                        data.getDescription().toString(),
                                        img_url,
                                        data.getId().toString(),
                                        v);
                            }

                        });
            }

        })

                .register(R.layout.menu_detail_adapter, new SlimInjector<USER>() {
                    @Override
                    public void onInject(USER data, IViewInjector injector) {
                        injector.text(R.id.textView_add, data.getName().toString());
                        String SHSH = data.getName().toString();
                        String SHASH = data.getName().toString();

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
    public void callNumber(String numberCall) {

        Intent call = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numberCall, null));
        startActivity(call);

    }

    @Override
    public void callNumberIncorrect() {
        Toast.makeText(getApplicationContext(),R.string.numberIncorrect,Toast.LENGTH_LONG).show();

    }

    @Override
    public void checkQuantity() {
        Toast.makeText(getApplicationContext(),R.string.check_quantity,Toast.LENGTH_LONG).show();

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
}

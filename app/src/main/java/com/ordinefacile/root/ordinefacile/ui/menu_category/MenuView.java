package com.ordinefacile.root.ordinefacile.ui.menu_category;

import android.content.Context;

import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public interface MenuView {

    void getStoreId();

    void getListStoreCategories(List<CategoriesDataModel> feedItemList);

    void dissapearSwipeToRefresh();

    void showSendingSms();

    void showErrorSending(String s);

    void goToDetail(Context context);
}



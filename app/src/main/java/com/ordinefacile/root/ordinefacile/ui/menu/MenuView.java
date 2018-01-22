package com.ordinefacile.root.ordinefacile.ui.menu;

import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategories;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategoriesData;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public interface MenuView {
    void getStoreId();
    void getListStoreCategories(List<StoreCategoriesData> feedItemList);
}

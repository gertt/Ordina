package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategoriesData;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public interface MenuDetailView {
    void getMenuDetails();
    void getListDished(List<MenuDishesDatum> feedItemList);
}

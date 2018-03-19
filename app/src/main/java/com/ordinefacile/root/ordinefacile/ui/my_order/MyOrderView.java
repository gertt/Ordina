package com.ordinefacile.root.ordinefacile.ui.my_order;

import com.ordinefacile.root.ordinefacile.data.db.Orders;

import java.util.List;

/**
 * Created by Eljo on 2/1/2018.
 */

public interface MyOrderView {

    void listAdapter(List<Orders> feedItemList);


    void idProduct(int int_product);
}

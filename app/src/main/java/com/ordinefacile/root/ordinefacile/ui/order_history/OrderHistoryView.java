package com.ordinefacile.root.ordinefacile.ui.order_history;

import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;

import java.util.List;

/**
 * Created by user on 3/12/2018.
 */

public interface OrderHistoryView {

    void listHistoryOrder(List<OrderHistoryData> feedItemList);

    void errorLoading();
}

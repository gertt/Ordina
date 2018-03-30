package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryItem;

import java.util.List;

/**
 * Created by user on 3/30/2018.
 */

@SuppressLint("ValidFragment")
public  class OrderHistoryDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private OrderHistoryDialogFragmentAdapter adapter;
    private List<OrderHistoryItem> feedItemList;
    // this method create view for your Dialog

    @SuppressLint("ValidFragment")
    public OrderHistoryDialogFragment(List<OrderHistoryItem> feedItemList) {

        this.feedItemList = feedItemList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout with recycler view
        View v = inflater.inflate(R.layout.layout_dialog_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycle_myorder_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //setadapter
        adapter = new OrderHistoryDialogFragmentAdapter(getActivity(), feedItemList);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //get your recycler view and populate it.
        return v;
    }
}

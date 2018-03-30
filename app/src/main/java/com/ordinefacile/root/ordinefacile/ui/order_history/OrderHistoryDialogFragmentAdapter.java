package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;

import java.util.List;

/**
 * Created by user on 3/30/2018.
 */

public class OrderHistoryDialogFragmentAdapter extends RecyclerView.Adapter<OrderHistoryDialogFragmentAdapter.ViewHolder>{

    private List<OrderHistoryData> feedItemList;
    private Context context;

    public OrderHistoryDialogFragmentAdapter(Context context, List<OrderHistoryData> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;

    }

    @Override
    public OrderHistoryDialogFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_detail_adapter_dialog, parent, false);
        return new OrderHistoryDialogFragmentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

       // final Person feedItem = feedItemList.get(position);

      //  holder.txt_name.setText("  "+feedItem.getEmri()+"  ");

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.textView5);

        }
    }
}
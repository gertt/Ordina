package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryItem;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import java.util.List;

/**
 * Created by user on 3/30/2018.
 */

public class OrderHistoryDialogFragmentAdapter extends RecyclerView.Adapter<OrderHistoryDialogFragmentAdapter.ViewHolder>{

    private List<OrderHistoryItem> feedItemList;
    private Context context;
    ParseImage parseImage;

    public OrderHistoryDialogFragmentAdapter(Context context, List<OrderHistoryItem> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseImage = new ParseImage(context);

    }

    @Override
    public OrderHistoryDialogFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_detail_adapter_dialog, parent, false);
        return new OrderHistoryDialogFragmentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final OrderHistoryItem feedItem = feedItemList.get(position);
        holder.img_bacground_dialog.setBackgroundColor(position % 2 == 0 ? Color.parseColor("#00D26A"): Color.parseColor("#F29C20"));

            holder.txt_name.setText("  "+feedItem.getName()+"  ");
            holder.txt_name_dialog.setText("Total Price  "+feedItem.getTotal()+"  ");
            holder.txt_price_dialog.setText("Price "+feedItem.getPrice()+"  ");
            holder.txt_quantity_dialog.setText("Quantity "+feedItem.getQuantity()+"  ");
            parseImage.parseimage(feedItem.getImage(),holder.imag_myorder_pick);

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_price_dialog;
        private TextView txt_quantity_dialog;
        private TextView txt_name_dialog;
        private ImageView img_bacground_dialog;
        private CircularImageView imag_myorder_pick;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.textView_myorder_name_history_dialog);
            img_bacground_dialog = (ImageView) itemView.findViewById(R.id.imageView_myorder_history_dialog);
            imag_myorder_pick = (CircularImageView) itemView.findViewById(R.id.circularImageView_myorder_history_dialog);
            txt_name_dialog = (TextView) itemView.findViewById(R.id.textView_myorder_history_dialog);
            txt_price_dialog = (TextView) itemView.findViewById(R.id.textView_myorder_metric_history_dialog);
            txt_quantity_dialog = (TextView) itemView.findViewById(R.id.textView_myorder_quantity_history_dialog);
        }
    }
}
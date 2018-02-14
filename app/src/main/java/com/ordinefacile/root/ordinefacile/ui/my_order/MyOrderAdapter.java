package com.ordinefacile.root.ordinefacile.ui.my_order;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;

import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailPresenter;
import com.ordinefacile.root.ordinefacile.utils.GlideApp;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import com.ordinefacile.root.ordinefacile.utils.Util;

import java.util.List;

/**
 * Created by Eljo on 2/1/2018.
 */

public class MyOrderAdapter   extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder>  {

    private static final String TAG = "My Debugg";
    private List<Orders> feedItemList;
    private Context context;
    private ParseImage parseimage;

    public MyOrderAdapter(Context context, List<Orders> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseimage = new ParseImage(context);


    }

    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_my_order_adapter, parent, false);
        return new MyOrderAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyOrderAdapter.ViewHolder holder, int position) {

        final Orders feedItem = feedItemList.get(position);
        holder.txt_name.setText(feedItem.getmName());

        holder.txt_name.setText(feedItem.getmName());

        holder.txt_price.setText(feedItem.getmPrice());

        holder.txt_metric.setText(feedItem.getmMetric());
        holder.txt_metric.setText(feedItem.getmMetric());

        parseimage.parseimage(feedItem.getmUrl_Image().toString(),holder.imag_myorder_pick);

        holder.img_bacground.setBackgroundColor(position % 2 == 0 ? Color.parseColor("#00D26A"): Color.parseColor("#F29C20"));
        holder.btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v,"CLICLED INFO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

               for (int i = 0;i<feedItemList.size();i++){
                   Log.d(TAG,feedItemList.get(i).getmName());

               }
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_price;

        private CircularImageView imag_myorder_pick;

        private TextView txt_metric;
        private ImageButton btn_info;
        private ImageView img_bacground;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.textView_myorder_name);
            imag_myorder_pick = (CircularImageView) itemView.findViewById(R.id.circularImageView_myorder);
            txt_price = (TextView) itemView.findViewById(R.id.textView_myorder_price);
            txt_metric = (TextView) itemView.findViewById(R.id.textView_myorder_metric);
            btn_info = (ImageButton) itemView.findViewById(R.id.imageButton_myorder_info);
            img_bacground = (ImageView) itemView.findViewById(R.id.imageView_myorder);
        }
    }
}


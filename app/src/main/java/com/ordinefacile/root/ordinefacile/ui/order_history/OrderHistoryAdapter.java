package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.Orders;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.ui.dialog.MaterialDialog;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailAdapter;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderActivity;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderAdapter;
import com.ordinefacile.root.ordinefacile.ui.my_order.MyOrderPresenter;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by user on 3/12/2018.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>   {


    private static final String TAG = "My Debugg";
    private List<OrderHistoryData> feedItemList;
    private Context context;
    private ParseImage parseimage;
    MaterialDialog materialDialog;


    public OrderHistoryAdapter(Context context, List<OrderHistoryData> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseimage = new ParseImage(context);
        materialDialog = new MaterialDialog();

    }

    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_my_order_history_adapter, parent, false);

      //  myOrderPresenter = new MyOrderPresenter(context,myOrderActivity);
        return new OrderHistoryAdapter.ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final OrderHistoryAdapter.ViewHolder holder, int position) {

        final OrderHistoryData feedItem = feedItemList.get(position);
      //  holder.txt_name.setText(feedItem.getmName());
      //  holder.txt_name.setText(feedItem.getmName());

        //String final_price = String.valueOf(new DecimalFormat("##.##").format(feedItem.getmFinalPrice()));

      //  holder.txt_price.setText(final_price);
     //   holder.txt_metric.setText(feedItem.getmMetric());
     //   holder.txt_metric.setText(feedItem.getmMetric());

       // parseimage.parseimage(feedItem.getmUrl_Image().toString(),holder.imag_myorder_pick);

      //  holder.img_bacground.setBackgroundColor(position % 2 == 0 ? Color.parseColor("#00D26A"): Color.parseColor("#F29C20"));




    }
    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_price;
        private TextView txt_metric;

        private CircularImageView imag_myorder_pick;

        private ImageButton btn_info;

        private ImageView btn_delete;
        private ImageView img_bacground;

        public ViewHolder(View itemView) {
            super(itemView);

         //   txt_name = (TextView) itemView.findViewById(R.id.textView_myorder_name);
         //   imag_myorder_pick = (CircularImageView) itemView.findViewById(R.id.circularImageView_myorder);
         //   txt_price = (TextView) itemView.findViewById(R.id.textView_myorder_price);
         //   txt_metric = (TextView) itemView.findViewById(R.id.textView_myorder_metric);
        //    btn_info = (ImageButton) itemView.findViewById(R.id.imageButton_myorder_info);
         //   img_bacground = (ImageView) itemView.findViewById(R.id.imageView_myorder);
         //   btn_delete = (ImageView) itemView.findViewById(R.id.imageButton_delete);
        }
    }


}




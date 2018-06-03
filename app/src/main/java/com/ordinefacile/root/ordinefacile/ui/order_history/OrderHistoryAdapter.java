package com.ordinefacile.root.ordinefacile.ui.order_history;

import android.content.Context;
import android.graphics.Color;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryData;
import com.ordinefacile.root.ordinefacile.data.network.model.OrderHistoryItem;
import com.ordinefacile.root.ordinefacile.ui.dialog.MaterialDialog;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import java.util.ArrayList;
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

    FragmentManager fragmentManager;

    ArrayList<OrderHistoryItem> feedItemListItem ;


    public OrderHistoryAdapter(Context context, List<OrderHistoryData> feedItemList,FragmentManager fragmentManager) {

        this.feedItemList = feedItemList;
        this.fragmentManager = fragmentManager;
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
        holder.img_bacground.setBackgroundColor(position % 2 == 0 ? Color.parseColor("#00D26A"): Color.parseColor("#F29C20"));

        parseimage.parseimage(feedItem.getStoreImage().toString(),holder.imag_myorder_pick);
        holder.txt_name.setText(feedItem.getStore());
        holder.txt_status.setText("Status "+feedItem.getStatus());
        holder.txt_price.setText("Price"+" "+feedItem.getTotalPrice()+" €");
        holder.txt_totalitems.setText("Total Items"+" "+feedItem.getTotalItems());




    //    for ( int i =0;i<feedItemListItem.size();i++) {
    //        Toast.makeText(context,feedItemListItem.get(i).getTotal().toString(),Toast.LENGTH_LONG).show();
   //     }

        holder.img_bacground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                feedItemListItem = new ArrayList<OrderHistoryItem>();
                for ( int i =0;i<feedItem.getItems().size();i++) {

                    feedItemListItem.add(feedItem.getItems().get(i));

                }
                for ( int i =0;i<feedItemListItem.size();i++) {

                }
                OrderHistoryDialogFragment newFragment = new OrderHistoryDialogFragment(feedItemListItem);
                newFragment.show(fragmentManager, "abc");

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private TextView txt_status;
        private TextView txt_price;
        private TextView txt_totalitems;
        private CircularImageView imag_myorder_pick;
        private ImageButton btn_info;
        private TextView txt_quantity;
        private ImageView img_bacground;


        public ViewHolder(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.textView_myorder_name_history);
            imag_myorder_pick = (CircularImageView) itemView.findViewById(R.id.circularImageView_myorder_history);
            txt_status = (TextView) itemView.findViewById(R.id.textview_status_history);
            txt_price = (TextView) itemView.findViewById(R.id.textView_myorder_price_history);
            txt_totalitems = (TextView) itemView.findViewById(R.id.textview_totalitems_history);
        //    btn_info = (ImageButton) itemView.findViewById(R.id.imageButton_myorder_info);
            img_bacground = (ImageView) itemView.findViewById(R.id.imageView_myorder_history);
        //  txt_quantity = (TextView) itemView.findViewById(R.id.textView_myorder_metric_history);
        }
    }


}




package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.utils.GlideApp;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import com.ordinefacile.root.ordinefacile.utils.Util;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MenuDetailAdapter extends RecyclerView.Adapter<MenuDetailAdapter.ViewHolder>{

    private List<MenuDishesDatum> feedItemList;
    private Context context;
    private ParseImage parseimage;


    public MenuDetailAdapter(Context context, List<MenuDishesDatum> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseimage = new ParseImage(context);

    }

    @Override
    public MenuDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_detail_adapter, parent, false);
        return new MenuDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MenuDishesDatum feedItem = feedItemList.get(position);
        holder.txt_name.setText("  "+feedItem.getName()+"  ");
        holder.price.setText("  "+feedItem.getPrice()+"  ");
        holder.metric.setText("  "+feedItem.getMetrics()+"  ");
        holder.description.setText("  "+feedItem.getDescription()+"  ");

        for(int i = 0 ;i<feedItem.getImages().size();i++){

            parseimage.parseimage(feedItem.getImages().get(i).getPath(),holder.imageviews);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Recycle Click  "+feedItem.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;
        private ImageView imageviews;
        private TextView price;
        private TextView metric;
        private TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.textView5);
            imageviews = (ImageView) itemView.findViewById(R.id.imageView2);
            price = (TextView) itemView.findViewById(R.id.textView_price);
            metric = (TextView) itemView.findViewById(R.id.textView_metric);
            description = (TextView) itemView.findViewById(R.id.textView_description);
        }
    }
}
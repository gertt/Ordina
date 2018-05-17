package com.ordinefacile.root.ordinefacile.ui.push_history;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistory;
import java.util.List;

/**
 * Created by user on 4/26/2018.
 */

public class PushHistoryAdapter  extends RecyclerView.Adapter<PushHistoryAdapter.ViewHolder>  {

    private static final String TAG = "My Debugg";
    private List<PushHistory> feedItemList;
    private Context context;
    PushHistoryActivity pushHistoryActivity;
    PushHistoryPresenter pushHistoryPresenter;

    public PushHistoryAdapter(Context context, List<PushHistory> feedItemList,PushHistoryActivity pushHistoryActivity) {

        this.feedItemList = feedItemList;
        this.context = context;
        this.pushHistoryActivity = pushHistoryActivity;

    }

    @Override
    public PushHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_push_adapter, parent, false);



        return new PushHistoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PushHistoryAdapter.ViewHolder holder, int position) {

        final PushHistory feedItem = feedItemList.get(position);
        holder.txt_tittle.setText(feedItem.getTittle());
        holder.txt_description.setText(feedItem.getDescriptions());
        holder.txt_price.setText(feedItem.getPrice());

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_tittle;
        private TextView txt_description;
        private TextView txt_price;


        public ViewHolder(View itemView) {
            super(itemView);

          txt_tittle = (TextView) itemView.findViewById(R.id.textView_tittle);
          txt_description = (TextView) itemView.findViewById(R.id.textView_descriptions);
          txt_price = (TextView) itemView.findViewById(R.id.textView_price);

        }
    }

}


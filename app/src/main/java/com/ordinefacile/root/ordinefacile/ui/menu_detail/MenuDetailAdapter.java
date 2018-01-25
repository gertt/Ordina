package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MenuDetailAdapter extends RecyclerView.Adapter<MenuDetailAdapter.ViewHolder>{

    private List<MenuDishesDatum> feedItemList;
    private Context context;

    public MenuDetailAdapter(Context context, List<MenuDishesDatum> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;

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

        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);

        }
    }
}
package com.ordinefacile.root.ordinefacile.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.StoreCategoriesData;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MenuActivityAdapter extends RecyclerView.Adapter<MenuActivityAdapter.ViewHolder>{

    private List<StoreCategoriesData> feedItemList;
    private Context context;

    public MenuActivityAdapter(Context context, List<StoreCategoriesData> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_adapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final StoreCategoriesData feedItem = feedItemList.get(position);
        holder.txt_name.setText("  "+feedItem.getName()+"  ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, MenuDetailActivity.class);
                i.putExtra("categoryId",feedItem.getId()+"");
                context.startActivity(i);

                Toast.makeText(context, "Recycle Click  "+holder.txt_name.getText().toString()+" "+feedItem.getId(), Toast.LENGTH_SHORT).show();
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
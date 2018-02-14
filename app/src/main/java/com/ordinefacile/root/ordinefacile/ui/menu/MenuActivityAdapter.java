package com.ordinefacile.root.ordinefacile.ui.menu;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.utils.GlideApp;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import com.ordinefacile.root.ordinefacile.utils.Util;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MenuActivityAdapter extends RecyclerView.Adapter<MenuActivityAdapter.ViewHolder>{

    private List<CategoriesDataModel> feedItemList;
    private Context context;
    ParseImage parseImage;

    public MenuActivityAdapter(Context context, List<CategoriesDataModel> feedItemList) {
        this.feedItemList = feedItemList;
        this.context = context;
        parseImage =new ParseImage(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_adapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CategoriesDataModel feedItem = feedItemList.get(position);

        for(int i = 0 ;i<feedItem.getImages().size();i++){

         //   holder.imageView.setBackgroundResource(R.drawable.photoholder);

            parseImage.parseimage(feedItem.getImages().get(i).getPath(),holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,MenuDetailActivity.class);
                intent.putExtra("categoryId", feedItem.getId()+"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;



        public ViewHolder(View itemView) {
        super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
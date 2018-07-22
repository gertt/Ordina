package com.ordinefacile.root.ordinefacile.ui.menu_category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.CategoriesDataModel;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.ui.menu_detail.MenuDetailActivity;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;
import org.greenrobot.eventbus.EventBus;
import java.util.List;

/**
 * Created by Eljo on 6/3/2018.
 */

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    private static final String TAG = "My Debugg";
    private List<CategoriesDataModel> feedItemList;
    private Context context;
    private ParseImage parseimage;

    MenuActivity menuActivity;
    SaveData saveData;


    public CategoryAdapter(Context context, List<CategoriesDataModel> feedItemList) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseimage = new ParseImage(context);
        menuActivity = new MenuActivity();
        saveData = new SaveData(context);


    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_adapter, parent, false);

       // menuActivity = new MenuActivity(context,menuActivity);
        return new CategoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder holder, int position) {

        final CategoriesDataModel feedItem = feedItemList.get(position);


        parseimage.parseimage(feedItem.getImage().toString(),holder.img_bacground);
        holder.txt_name.setText(feedItem.getName().toUpperCase());

        holder.img_bacground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                saveData.saveDishesId(feedItem.getId().toString());

                Intent intent = new Intent(context,MenuDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name;

        private ImageView img_bacground;

        public ViewHolder(View itemView) {
            super(itemView);

        txt_name = (TextView) itemView.findViewById(R.id.textView4);
            img_bacground = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

}


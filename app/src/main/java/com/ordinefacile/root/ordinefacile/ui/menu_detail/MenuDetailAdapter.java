package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.ordinefacile.root.ordinefacile.R;
import com.ordinefacile.root.ordinefacile.data.network.model.MenuDishesDatum;
import com.ordinefacile.root.ordinefacile.data.prefs.SaveData;
import com.ordinefacile.root.ordinefacile.utils.ParseImage;

import java.util.List;

/**
 * Created by user on 1/22/2018.
 */

public class MenuDetailAdapter extends RecyclerView.Adapter<MenuDetailAdapter.ViewHolder>{

    private List<MenuDishesDatum> feedItemList;
    private Context context;
    private ParseImage parseimage;
    int score = 0 ;

    MenuDetailPresenter menuDetailPresenter;
    String urlImg;
    String id_product;
    String name;
    SaveData saveData;
    ViewHolder holder1;
    Float final_price;


    public MenuDetailAdapter(Context context, List<MenuDishesDatum> feedItemList,  MenuDetailPresenter menuDetailPresenter) {

        this.feedItemList = feedItemList;
        this.context = context;
        parseimage = new ParseImage(context);
        this.menuDetailPresenter = menuDetailPresenter;
        saveData =  new SaveData(context);

    }

    @Override
    public MenuDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.menu_detail_adapter, parent, false);
        return new MenuDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder1 = holder;
        MenuDishesDatum  feedItem = feedItemList.get(position);

        holder.txt_name.setText("  "+feedItem.getName()+"  ");
        holder.price.setText("  "+feedItem.getPrice()+" €");
        // holder.metric.setText("  "+feedItem.getMetrics()+"  ");

        if (feedItem.getDescription()==null){

            holder.description.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        }else {

            holder.description.setText("  "+feedItem.getDescription()+"  ");
        }



        //  if (feedItem.getMetrics()==null){

        //     holder.metric.setText(" ");
        //  }else {

        //      holder.metric.setText("  "+feedItem.getMetrics()+"  ");
        //  }



        Integer emir = feedItem.getId();
        parseimage.parseimage(feedItem.getImage().toString(), holder.imageviews);
        urlImg = feedItem.getImage().toString();
        name = feedItem.getName();
        id_product = feedItem.getProductCategoryId();

        holder.btn_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textnumberstring = holder.txt_add.getText().toString();

                if (textnumberstring.equalsIgnoreCase("Add")) {

                    int score = 0;
                    int numberInt = score;
                    numberInt++;
                    holder.txt_add.setText("" + numberInt);

                    Float quantity_float = Float.valueOf(numberInt);
                    Float quantity_price =Float.parseFloat(feedItem.getPrice());
                    Float final_price = quantity_price * quantity_float;

                    String id_product_cart = feedItem.getId().toString();



                    menuDetailPresenter.update(final_price,quantity_float,  holder.txt_name.getText().toString(),quantity_price,
                            feedItem.getDescription(),feedItem.getImage(),saveData.getEntity(),feedItem.getId().toString(),id_product_cart,quantity_float.toString());


                } else if (!textnumberstring.equalsIgnoreCase("Add")) {
                    int score = Integer.parseInt(textnumberstring);
                    int numberInt = score;

                    numberInt++;
                    holder.txt_add.setText("" + numberInt);

                    Float quantity_float = Float.valueOf(numberInt);
                    Float quantity_price =Float.parseFloat(feedItem.getPrice());
                    Float final_price = quantity_price * quantity_float;
                    String id_product_cart = feedItem.getId().toString();


                    menuDetailPresenter.update(final_price,quantity_float,  holder.txt_name.getText().toString(),quantity_price,
                            feedItem.getDescription(),feedItem.getImage(),saveData.getEntity(),
                            feedItem.getId().toString(),id_product_cart,quantity_float.toString());

                }


            }
        });

        holder.btn_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textnumberstring = holder.txt_add.getText().toString();

                if (textnumberstring.equalsIgnoreCase("Add")) {

                    holder.txt_add.setText("" + "Add");


                } else {
                    score = Integer.parseInt(textnumberstring);
                }

                if (score == 1) {

                    holder.txt_add.setText("" + "Add");


                    int int_product = Integer.parseInt(feedItem.getId().toString());
                    menuDetailPresenter.delete(int_product);

                } else if (score > 1) {

                    score = Integer.parseInt(textnumberstring);
                    int numberInt = score;

                    numberInt--;
                    holder.txt_add.setText("" + numberInt);

                    Float quantity_float = Float.valueOf(numberInt);

                    Float quantity_price =Float.parseFloat(feedItem.getPrice());
                    Float final_price = quantity_price * quantity_float;
                    String id_product_cart = feedItem.getId().toString();
                    menuDetailPresenter.update(final_price,quantity_float,  holder.txt_name.getText().toString(),quantity_price,
                            feedItem.getDescription(),feedItem.getImage(),saveData.getEntity(),
                            feedItem.getId().toString(),id_product_cart,quantity_float.toString());

                }



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
        private  TextView txt_add;
        private TextView description;
        private Button btn_increment;
        private Button btn_decrement;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.textView5);
            imageviews = (ImageView) itemView.findViewById(R.id.imageView2);
            price = (TextView) itemView.findViewById(R.id.textView_price);
            metric = (TextView) itemView.findViewById(R.id.textView_metric);
            description = (TextView) itemView.findViewById(R.id.textView_description);
            btn_increment = (Button) itemView.findViewById(R.id.button_increment);
            btn_decrement = (Button) itemView.findViewById(R.id.button_decrement);
            txt_add = (TextView) itemView.findViewById(R.id.textView_add);

        }
    }
}
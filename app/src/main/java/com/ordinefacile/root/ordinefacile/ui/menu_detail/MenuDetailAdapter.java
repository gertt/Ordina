package com.ordinefacile.root.ordinefacile.ui.menu_detail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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

        final MenuDishesDatum feedItem = feedItemList.get(position);

        holder.txt_name.setText("  "+feedItem.getName()+"  ");
        holder.price.setText("  "+feedItem.getPrice()+"  ");
        holder.metric.setText("  "+feedItem.getMetrics()+"  ");
        holder.description.setText("  "+feedItem.getDescription()+"  ");

        Integer emir = feedItem.getId();



        parseimage.parseimage(feedItem.getImage().toString(), holder.imageviews);
        urlImg = feedItem.getImage().toString();
        name = feedItem.getName();
        id_product = feedItem.getProductCategoryId();

    //    for(int i = 0 ;i<feedItem.getImages().size();i++) {
    //        parseimage.parseimage(feedItem.getImages().get(i).getPath(), holder.imageviews);
     //       urlImg = feedItem.getImages().get(i).getPath().toString();
    //        id_product = feedItem.getImages().get(i).getProductId();

   //     }




      //  String jsjus =     saveData.getNumberCharacter();
      //  String jsjZus =     saveData.getNumberCharacter();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String numberAsString = String.valueOf(feedItem.getId());

                String djdh =  id_product;

                System.out.print(id_product);
                System.out.print(name);

                String djdj = holder.txt_name.getText().toString();

                menuDetailPresenter.checkQuantityOrGoActivity(
                        holder.txt_add.getText().toString(),
                        holder.txt_name.getText().toString(),
                        holder.price.getText().toString(),
                        holder.metric.getText().toString(),
                        holder.description.getText().toString(),urlImg,numberAsString, v);


            }
        });


        holder.btn_increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textnumberstring = holder.txt_add.getText().toString();

                if (textnumberstring.equalsIgnoreCase("Add")){

                    int score = 0 ;
                    int numberInt = score;

                       numberInt++;
                        holder.txt_add.setText(""+numberInt);

                }else if (!textnumberstring.equalsIgnoreCase("Add")){
                    int score = Integer.parseInt(textnumberstring);
                    int numberInt = score;

                    numberInt++;
                    holder.txt_add.setText(""+numberInt);

                }
                Snackbar.make(v,holder.txt_name.getText(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        holder.btn_decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textnumberstring = holder.txt_add.getText().toString();

                if (textnumberstring.equalsIgnoreCase("Add")){

                    holder.txt_add.setText(""+"Add");

                }else {
                    score = Integer.parseInt(textnumberstring);
                }

                if (score==1){

                    holder.txt_add.setText(""+"Add");
                }else  if (score>1){

                    score = Integer.parseInt(textnumberstring);
                    int numberInt = score;

                    numberInt--;
                    holder.txt_add.setText(""+numberInt);

                }

                Snackbar.make(v,holder.txt_name.getText(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
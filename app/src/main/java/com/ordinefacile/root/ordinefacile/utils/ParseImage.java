package com.ordinefacile.root.ordinefacile.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ordinefacile.root.ordinefacile.R;

/**
 * Created by root on 1/23/18.
 */

public class ParseImage {

    Context context;
    public ParseImage(Context context) {
        this.context = context;

    }

    public   void parseimage(String url, ImageView imageView){

        String  input = url.toString();
        input = input.replace(" ", "");
        Glide.with(context)
                .load("http://restaurant.softmate.org/"+input)
       // .load("http://restaurant.softmate.org/storage/images/product_categories/e3ea51dd047185745f2d7fe86f70b0b139694197.jpeg")
                //.centerCrop()
                //.placeholder(R.drawable.photoholder)
                .into(imageView);
    }

}

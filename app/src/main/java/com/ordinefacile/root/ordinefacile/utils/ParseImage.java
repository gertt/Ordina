package com.ordinefacile.root.ordinefacile.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        GlideApp.with(context)
                .load(Util.IMAGE_URL+input)
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(imageView);

    }

}

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
        GlideApp.with(context)
                .load(Util.IMAGE_URL+input)
                .override(400,400)
                .into(imageView);

    }

}

package com.ordinefacile.root.ordinefacile.utils;

import android.content.Context;
import android.widget.ImageView;
import com.ordinefacile.root.ordinefacile.R;
import static com.ordinefacile.root.ordinefacile.utils.Util.IMAGE_URL2;

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
        input = input.trim();
        GlideApp.with(context)
                .load(IMAGE_URL2+input)
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(imageView);
    }
}

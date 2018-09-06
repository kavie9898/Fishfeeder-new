package com.fishfeeder_new.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.fishfeeder_new.R;


public class PicassoClient {

    public static void downloadImage(Context c,String image,ImageView img)
    {
        if(image.length()>0 && image!=null)
        {
            Picasso.with(c).load(image).placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }

}

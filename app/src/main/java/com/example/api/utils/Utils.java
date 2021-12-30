package com.example.api.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Utils {

    public static Bitmap convertImageViewToBitmap(ImageView v){
        Bitmap bm = ((BitmapDrawable)v.getDrawable()).getBitmap();
        return bm;
    }

    public static Drawable convertBitmapToImagenView(Bitmap bitmap, Context context){
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }

}
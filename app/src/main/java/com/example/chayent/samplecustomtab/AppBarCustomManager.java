package com.example.chayent.samplecustomtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;

/**
 * AppBarCustomManager.java
 * SampleCustomTab
 * Created by Chayen Tansritrang on 8/20/2018.
 * Copyright © Electronics Extreme Ltd. All rights reserved.
 */
public class AppBarCustomManager {

    private Activity mActivity;
    private AppBarLayout mAppBarLayout;

    AppBarCustomManager(Activity mActivity, AppBarLayout mAppBarLayout) {
        this.mActivity = mActivity;
        this.mAppBarLayout = mAppBarLayout;
    }

    public void setImageBackground(int imageId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setBackground(resizeImage(mActivity.getDrawable(imageId), mAppBarLayout.getWidth(), mAppBarLayout.getHeight()));
//                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.poring_background));
        }
    }

    public void setColorBackground(int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAppBarLayout.setBackgroundColor(mActivity.getColor(colorId));
        }
    }

    public void setIconImage(Menu menu, int imageId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            menu.getItem(0).setIcon(mActivity.getDrawable(imageId));
        }
    }

    private Drawable resizeImage(Drawable image, int scaleX, int scaleY) {
        if ((image == null) || !(image instanceof BitmapDrawable)) {
            return image;
        }
        Bitmap imageBitmap = ((BitmapDrawable) image).getBitmap();

//        int sizeX = Math.round(image.getIntrinsicWidth() * scaleFactor);
//        int sizeY = Math.round(image.getIntrinsicHeight() * scaleFactor);

        if (imageBitmap.getWidth() <= scaleX) {
            scaleX = imageBitmap.getWidth();
        }
        if (imageBitmap.getHeight() <= scaleY) {
            scaleY = imageBitmap.getHeight();
        }
        Bitmap bitmapCrop = Bitmap.createBitmap(imageBitmap, 0, 0, scaleX, scaleY);
//        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
        image = new BitmapDrawable(mActivity.getResources(), bitmapCrop);
        return image;
    }
}

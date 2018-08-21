package com.example.chayent.samplecustomtab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
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
    private android.support.v7.widget.Toolbar mToolbar;

    AppBarCustomManager(Activity mActivity, AppBarLayout mAppBarLayout) {
        this.mActivity = mActivity;
        this.mAppBarLayout = mAppBarLayout;
    }

    public AppBarCustomManager setToolbar(android.support.v7.widget.Toolbar toolbar) {
        this.mToolbar = toolbar;
        setToolBar();
        return this;
    }

    private void setToolBar() {
        mToolbar.setPadding(24, AppConstant.SCALE_DEFAULT_VALUE, AppConstant.SCALE_DEFAULT_VALUE, AppConstant.SCALE_DEFAULT_VALUE);
        ((AppCompatActivity)mActivity).setSupportActionBar(mToolbar);
        ((AppCompatActivity)mActivity).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)mActivity).getSupportActionBar().setIcon(R.drawable.ic_action_name);
    }

    public void setImageBackground(int imageId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setBackground(resizeImage(mActivity.getDrawable(imageId), mAppBarLayout.getWidth(), mAppBarLayout.getHeight()));
//                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.poring_background));
        }
    }

    public void setColorBackground(int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mAppBarLayout.setBackgroundColor(mActivity.getColor(colorId));
        }
    }

    public void setIconImage(Menu menu, int imageId, int itemIndex) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            menu.getItem(itemIndex).setIcon(mActivity.getDrawable(imageId));
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
        Bitmap bitmapCrop = Bitmap.createBitmap(imageBitmap, AppConstant.SCALE_DEFAULT_VALUE, AppConstant.SCALE_DEFAULT_VALUE, scaleX, scaleY);
//        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
        image = new BitmapDrawable(mActivity.getResources(), bitmapCrop);
        return image;
    }
}

package com.example.chayent.samplecustomtab;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.chayent.samplecustomtab.enumerator.ImageResourceTab;
import com.example.chayent.samplecustomtab.enumerator.TabHeaderValue;

import java.lang.reflect.Method;

/**
 * TabCustomManager.java
 * SampleCustomTab
 * Created by Chayen Tansritrang on 8/10/2018.
 * Copyright © Electronics Extreme Ltd. All rights reserved.
 */
public class TabCustomManager {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Activity mActivity;
    private TextView mTabTextHeader;
    private LottieAnimationView mTabAnimationHeader;
    private ImageView mTabImageHeader;
    private FrameLayout mCustomTabItemLayout;
    private String mThemeName = "";
    private int mPreviousIndex = 0;

    TabCustomManager(ViewPager mViewPager, TabLayout mTabLayout, Activity mActivity) {
        this.mViewPager = mViewPager;
        this.mTabLayout = mTabLayout;
        this.mActivity = mActivity;
    }

    public void setDefaultTab() {
        mThemeName = "DEFAULT";
        mTabLayout.setupWithViewPager(mViewPager);
        createCustomTab();
        mTabLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimary));
        mTabLayout.setSelectedTabIndicatorColor(mActivity.getResources().getColor(R.color.colorAccent));
//        mTabLayout.getTabAt(mPreviousIndex).select();
    }

    public void setImageTab(String themeName) {
        mThemeName = themeName;
        mTabLayout.setupWithViewPager(mViewPager);
        if (themeName.equals("ANIMATION")) {
            createCustomTab(R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance);
        } else {
            for (ImageResourceTab imageResourceTab : ImageResourceTab.values()) {
                if (imageResourceTab.name().equals(themeName)) {
                    createCustomTab(resizeImage(imageResourceTab.getIcFeature()),
                            resizeImage(imageResourceTab.getIcGame()),
                            resizeImage(imageResourceTab.getIcSocial()),
                            resizeImage(imageResourceTab.getIcMarket()));
                }
            }
        }
        switch (themeName) {
            case "PORING":
                mTabLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
                mTabLayout.setSelectedTabIndicatorColor(mActivity.getResources().getColor(R.color.colorPrimaryDark));
                break;
            case "POKEMON":
                mTabLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPokemon));
                mTabLayout.setSelectedTabIndicatorColor(mActivity.getResources().getColor(R.color.colorPrimaryDark));
                break;
            case "ANIMATION":
                mTabLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPokemon));
                mTabLayout.setSelectedTabIndicatorColor(mActivity.getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
//        mTabLayout.getTabAt(mPreviousIndex).select();
    }

    private void createCustomTab() {
        setTabHeader(TabHeaderValue.FEATURED.getTabIndex(), TabHeaderValue.FEATURED.getTabHeaderText());
        setTabHeader(TabHeaderValue.GAME.getTabIndex(), TabHeaderValue.GAME.getTabHeaderText());
        setTabHeader(TabHeaderValue.SOCIAL.getTabIndex(), TabHeaderValue.SOCIAL.getTabHeaderText());
        setTabHeader(TabHeaderValue.MARKET.getTabIndex(), TabHeaderValue.MARKET.getTabHeaderText());
    }

    private void createCustomTab(Drawable imageFeature, Drawable imageGame, Drawable imageSocial, Drawable imageMarket) {
        setTabHeader(TabHeaderValue.FEATURED.getTabIndex(), TabHeaderValue.FEATURED.getTabHeaderText(), imageFeature);
        setTabHeader(TabHeaderValue.GAME.getTabIndex(), TabHeaderValue.GAME.getTabHeaderText(), imageGame);
        setTabHeader(TabHeaderValue.SOCIAL.getTabIndex(), TabHeaderValue.SOCIAL.getTabHeaderText(), imageSocial);
        setTabHeader(TabHeaderValue.MARKET.getTabIndex(), TabHeaderValue.MARKET.getTabHeaderText(), imageMarket);
    }

    private void createCustomTab(int dino_dance, int dino_dance1, int dino_dance2, int dino_dance3) {
        setTabHeader(TabHeaderValue.FEATURED.getTabIndex(), TabHeaderValue.FEATURED.getTabHeaderText(), dino_dance);
        setTabHeader(TabHeaderValue.GAME.getTabIndex(), TabHeaderValue.GAME.getTabHeaderText(), dino_dance1);
        setTabHeader(TabHeaderValue.SOCIAL.getTabIndex(), TabHeaderValue.SOCIAL.getTabHeaderText(), dino_dance2);
        setTabHeader(TabHeaderValue.MARKET.getTabIndex(), TabHeaderValue.MARKET.getTabHeaderText(), dino_dance3);
    }

    private void setTabHeader(int tabIndex, String textHeader) {
        mCustomTabItemLayout = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.custom_tab, mTabLayout, false);
        mTabTextHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_text_view);
        mTabTextHeader.setText(textHeader);
        mTabLayout.getTabAt(tabIndex).setCustomView(mCustomTabItemLayout);
    }

    private void setTabHeader(int tabIndex, String textHeader, Drawable imageHeader) {
        mCustomTabItemLayout = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.custom_tab, mTabLayout, false);
        mTabTextHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_text_view);
        mTabTextHeader.setText(textHeader);
        mTabImageHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_image_view);
        RequestOptions options = new RequestOptions().centerCrop().override(80).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mActivity).load(imageHeader).apply(options).into(mTabImageHeader);
        mTabLayout.getTabAt(tabIndex).setCustomView(mCustomTabItemLayout);
    }

    private void setTabHeader(int tabIndex, String textHeader, int animationView) {
        mCustomTabItemLayout = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.custom_tab, mTabLayout, false);
        mTabTextHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_text_view);
        mTabTextHeader.setText(textHeader);
        mTabAnimationHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_animation_view);
//        LottieDrawable drawable = new LottieDrawable();
//        LottieComposition.Factory.fromRawFile(mActivity, animationView, new OnCompositionLoadedListener() {
//            @Override
//            public void onCompositionLoaded(@Nullable LottieComposition composition) {
//                drawable.setComposition(composition);
//                drawable.playAnimation();
//                drawable.setScale(0.3f);
//                mTabAnimationHeader.setImageDrawable(drawable);
//            }
//        });
//        mTabAnimationHeader.setScaleX(0.5f);
        //  mTabAnimationHeader.setScaleY(0.3f);
        // mTabAnimationHeader.setScale(0.3f);
        mTabAnimationHeader.setVisibility(View.VISIBLE);
//        mTabAnimationHeader.playAnimation();
        mTabAnimationHeader.setAnimation(animationView);
//        RequestOptions options = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(mActivity).load(animationView).apply(options).into(mTabAnimationHeader);
        mTabLayout.getTabAt(tabIndex).setCustomView(mCustomTabItemLayout);
    }

    private Drawable resizeImage(int imageID) {
        Drawable image = ContextCompat.getDrawable(mActivity, imageID);
        if (image != null) {
            image.setBounds(0, 0, 80, 80);
        }
        return image;
    }

    public void setTabListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPreviousIndex = tab.getPosition();
                switch (mThemeName) {
                    case "DEFAULT":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.color.colorWhite);
                        break;
                    case "PORING":
                    case "POKEMON":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_image_view, (float) 1.5, R.color.colorWhite, false);
                        break;
                    case "ANIMATION":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_animation_view, (float) 1.5, R.color.colorWhite, true);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (mThemeName) {
                    case "DEFAULT":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.color.colorBlack);
                        break;
                    case "PORING":
                    case "POKEMON":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_image_view, 1, R.color.colorBlack, false);
                        break;
                    case "ANIMATION":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_animation_view, 1, R.color.colorBlack, false);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mPreviousIndex = tab.getPosition();
                switch (mThemeName) {
                    case "DEFAULT":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.color.colorWhite);
                        break;
                    case "PORING":
                    case "POKEMON":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_image_view, (float) 1.5, R.color.colorWhite, false);
                        break;
                    case "ANIMATION":
                        updateTab(mTabLayout.getTabAt(tab.getPosition()), R.id.custom_tab_text_view, R.id.custom_tab_animation_view, (float) 1.5, R.color.colorWhite, true);
                        break;
                }
            }
        });
    }

    private void updateTab(TabLayout.Tab tab, int textId, int textColor) {
        Method method = null;
        try {
            method = TabLayout.Tab.class.getDeclaredMethod("getCustomView", null);
            method.setAccessible(true);

            View tabView = (View) method.invoke(tab, null);
            final TextView tabItemTextHeader = tabView.findViewById(textId);
            tabItemTextHeader.setTextColor(mActivity.getResources().getColor(textColor));

            tab.setCustomView(tabView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTab(TabLayout.Tab tab, int textId, int imageID, float imageSize, int textColor, boolean animationPlay) {
        Method method = null;
        try {
            method = TabLayout.Tab.class.getDeclaredMethod("getCustomView", null);
            method.setAccessible(true);

            View tabView = (View) method.invoke(tab, null);

            final TextView tabItemTextHeader = tabView.findViewById(textId);
            tabItemTextHeader.setTextColor(mActivity.getResources().getColor(textColor));

            if (!mThemeName.equals("ANIMATION")) {
                final ImageView tabItemImageHeader = tabView.findViewById(imageID);
                tabItemImageHeader.setScaleX(imageSize);
                tabItemImageHeader.setScaleY(imageSize);
            } else {
                final LottieAnimationView tabItemAnimationHeader = tabView.findViewById(imageID);
                if (animationPlay) {
                    if (!tabItemAnimationHeader.isAnimating()) {
                        tabItemAnimationHeader.setScaleX(imageSize);
                        tabItemAnimationHeader.setScaleY(imageSize);
                        tabItemAnimationHeader.setRepeatCount(LottieDrawable.INFINITE);
                        tabItemAnimationHeader.playAnimation();
                        // tabItemAnimationHeader.setRepeatMode(-1);
                    }
                } else {
                    tabItemAnimationHeader.setScaleX(imageSize);
                    tabItemAnimationHeader.setScaleY(imageSize);
                    tabItemAnimationHeader.pauseAnimation();
//                tabItemAnimationHeader.setFrame(0);
                }
            }

            tab.setCustomView(tabView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPreviousIndex(){
        return mPreviousIndex;
    }
}

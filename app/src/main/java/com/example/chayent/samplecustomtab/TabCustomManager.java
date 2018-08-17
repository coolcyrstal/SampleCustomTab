package com.example.chayent.samplecustomtab;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
    private int mPreviousIndex = -1;

    TabCustomManager(ViewPager mViewPager, TabLayout mTabLayout, Activity mActivity) {
        this.mViewPager = mViewPager;
        this.mTabLayout = mTabLayout;
        this.mActivity = mActivity;
    }

    public void setCustomTextTab() {
        mThemeName = "DEFAULT";
        mTabLayout.setupWithViewPager(mViewPager);
        createCustomTab();
        setTabPresentFocus();
    }

    public void setCustomImageTab(String themeName) {
        mThemeName = themeName;
        mTabLayout.setupWithViewPager(mViewPager);
        for (ImageResourceTab imageResourceTab : ImageResourceTab.values()) {
            if (imageResourceTab.name().equals(themeName)) {
                createCustomTab(imageResourceTab.getIcFeature(),
                        imageResourceTab.getIcGame(),
                        imageResourceTab.getIcSocial(),
                        imageResourceTab.getIcMarket());
            }
        }
        setTabPresentFocus();
    }

    public void setCustomAnimationTab(String themeName) {
        mThemeName = themeName;
        mTabLayout.setupWithViewPager(mViewPager);
        createCustomTab(R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance);
        setTabPresentFocus();
    }

    private void createCustomTab() {
        setTabHeader(TabHeaderValue.FEATURED.getTabIndex(), TabHeaderValue.FEATURED.getTabHeaderText());
        setTabHeader(TabHeaderValue.GAME.getTabIndex(), TabHeaderValue.GAME.getTabHeaderText());
        setTabHeader(TabHeaderValue.SOCIAL.getTabIndex(), TabHeaderValue.SOCIAL.getTabHeaderText());
        setTabHeader(TabHeaderValue.MARKET.getTabIndex(), TabHeaderValue.MARKET.getTabHeaderText());
    }

    private void createCustomTab(int imageFeature, int imageGame, int imageSocial, int imageMarket) {
        setTabHeader(TabHeaderValue.FEATURED.getTabIndex(), TabHeaderValue.FEATURED.getTabHeaderText(), imageFeature);
        setTabHeader(TabHeaderValue.GAME.getTabIndex(), TabHeaderValue.GAME.getTabHeaderText(), imageGame);
        setTabHeader(TabHeaderValue.SOCIAL.getTabIndex(), TabHeaderValue.SOCIAL.getTabHeaderText(), imageSocial);
        setTabHeader(TabHeaderValue.MARKET.getTabIndex(), TabHeaderValue.MARKET.getTabHeaderText(), imageMarket);
    }

    private void setTabHeader(int tabIndex, String textHeader) {
        mCustomTabItemLayout = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.custom_tab, mTabLayout, false);
        mTabTextHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_text_view);
        mTabTextHeader.setText(textHeader);
        mTabLayout.getTabAt(tabIndex).setCustomView(mCustomTabItemLayout);
    }

    private void setTabHeader(int tabIndex, String textHeader, int imageHeader) {
        mCustomTabItemLayout = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.custom_tab, mTabLayout, false);
        mTabTextHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_text_view);
        mTabTextHeader.setText(textHeader);
        if (mThemeName.equals("ANIMATION")) {
            mTabAnimationHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_animation_view);
            mTabAnimationHeader.setVisibility(View.VISIBLE);
            mTabAnimationHeader.setAnimation(imageHeader);
        } else {
            mTabImageHeader = mCustomTabItemLayout.findViewById(R.id.custom_tab_image_view);
            RequestOptions options = new RequestOptions().centerCrop().override(80).diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mActivity).load(imageHeader).apply(options).into(mTabImageHeader);
        }
        mTabLayout.getTabAt(tabIndex).setCustomView(mCustomTabItemLayout);
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

    public void setTabInitFocus() {
        mTabLayout.getTabAt(0).select();
    }

    private void setTabPresentFocus() {
        mTabLayout.getTabAt(getPreviousIndex()).select();
    }

    private int getPreviousIndex() {
        return mPreviousIndex;
    }

    public void setTabBackgroundColor(int colorId) {
        mTabLayout.setBackgroundColor(mActivity.getResources().getColor(colorId));
    }

    public void setTabIndicatorColor(int colorId) {
        mTabLayout.setSelectedTabIndicatorColor(mActivity.getResources().getColor(colorId));
    }
}

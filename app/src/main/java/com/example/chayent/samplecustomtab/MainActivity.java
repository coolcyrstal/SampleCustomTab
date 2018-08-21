package com.example.chayent.samplecustomtab;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chayent.samplecustomtab.adapter.ViewPagerAdapter;
import com.example.chayent.samplecustomtab.enumerator.TabHeaderValue;
import com.example.chayent.samplecustomtab.enumerator.ToolbarIconIndex;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.toolbar_text_title)
    TextView toolbarTextTitle;

    @BindView(R.id.btn_default_theme)
    Button btnDefaultTheme;
    @BindView(R.id.btn_pokemon_theme)
    Button btnPokemonTheme;
    @BindView(R.id.btn_poring_theme)
    Button btnPoringTheme;
    @BindView(R.id.btn_animation_theme)
    Button btnAnimationTheme;

    private TabCustomManager mTabCustomManager;
    private AppBarCustomManager mAppBarCustomManager;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAppBarCustomManager = new AppBarCustomManager(this, appBarLayout).setToolbar(toolbar);
        mTabCustomManager = new TabCustomManager(viewPager, tabLayout, this);
        createViewPager(viewPager);
        mTabCustomManager.setCustomTextTab();
        mTabCustomManager.setTabListener();
        mTabCustomManager.setTabInitFocus();

        setButton();
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.FEATURED.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.GAME.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.SOCIAL.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.MARKET.getTabHeaderText());
        viewPager.setAdapter(adapter);
    }

    private void setButton() {
        btnDefaultTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setCustomTextTab();
//                mTabCustomManager.setTabBackgroundColor(R.color.colorPrimary);
                mTabCustomManager.setTabIndicatorColor(R.color.colorAccent);
                mTabCustomManager.setTabPresentFocus();
                mAppBarCustomManager.setColorBackground(R.color.colorPrimary);
            }
        });

        btnPokemonTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setCustomImageTab(AppConstant.THEME_NAME_POKEMON);
//                mTabCustomManager.setTabBackgroundColor(R.color.colorPokemon);
                mTabCustomManager.setTabIndicatorColor(R.color.colorPrimaryDark);
                mTabCustomManager.setTabPresentFocus();
                mAppBarCustomManager.setImageBackground(R.drawable.pokemon_background);
                mAppBarCustomManager.setIconImage(mMenu, R.drawable.menu_pokemon_search, ToolbarIconIndex.SEARCH.getIconIndex());
            }
        });

        btnPoringTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setCustomImageTab(AppConstant.THEME_NAME_PORING);
//                mTabCustomManager.setTabBackgroundColor(R.color.colorPokemon);
                mTabCustomManager.setTabIndicatorColor(R.color.colorPrimaryDark);
                mTabCustomManager.setTabPresentFocus();
                mAppBarCustomManager.setImageBackground(R.drawable.poring_background);
            }
        });

        btnAnimationTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setCustomAnimationTab(AppConstant.THEME_NAME_ANIMATION);
//                mTabCustomManager.setTabBackgroundColor(R.color.colorPokemon);
                mTabCustomManager.setTabIndicatorColor(R.color.colorPrimaryDark);
                mTabCustomManager.setTabPresentFocus();
                mAppBarCustomManager.setColorBackground(R.color.colorPokemon);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_notification:
                return true;
            case R.id.action_feedback:
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}

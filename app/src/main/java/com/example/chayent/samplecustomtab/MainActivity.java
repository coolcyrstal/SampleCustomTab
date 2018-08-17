package com.example.chayent.samplecustomtab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chayent.samplecustomtab.adapter.ViewPagerAdapter;
import com.example.chayent.samplecustomtab.enumerator.TabHeaderValue;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//
//    @BindView(R.id.toolbar_text_title)
//    TextView toolbarTextTitle;

    @BindView(R.id.btn_default_theme)
    Button btnDefaultTheme;
    @BindView(R.id.btn_pokemon_theme)
    Button btnPokemonTheme;
    @BindView(R.id.btn_poring_theme)
    Button btnPoringTheme;
    @BindView(R.id.btn_animation_theme)
    Button btnAnimationTheme;

    private TabCustomManager mTabCustomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        setToolBar();
        mTabCustomManager = new TabCustomManager(viewPager, tabLayout, this);
        createViewPager(viewPager);
        mTabCustomManager.setDefaultTab();
        mTabCustomManager.setTabListener();
        tabLayout.getTabAt(0).select();

        btnDefaultTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setDefaultTab();
                tabLayout.getTabAt(mTabCustomManager.getPreviousIndex()).select();
            }
        });

        btnPokemonTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setImageTab("POKEMON");
                tabLayout.getTabAt(mTabCustomManager.getPreviousIndex()).select();
            }
        });

        btnPoringTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setImageTab("PORING");
                tabLayout.getTabAt(mTabCustomManager.getPreviousIndex()).select();
            }
        });

        btnAnimationTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabCustomManager.setImageTab("ANIMATION");
                tabLayout.getTabAt(mTabCustomManager.getPreviousIndex()).select();
            }
        });
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.FEATURED.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.GAME.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.SOCIAL.getTabHeaderText());
        adapter.addFrag(new TabLayoutFragment(), TabHeaderValue.MARKET.getTabHeaderText());
        viewPager.setAdapter(adapter);
    }

//    private void setToolBar(){
//        setSupportActionBar(toolbar);
//        toolbarTextTitle.setText("DEFAULT");
//    }
}
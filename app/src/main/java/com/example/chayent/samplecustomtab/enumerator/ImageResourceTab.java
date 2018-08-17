package com.example.chayent.samplecustomtab.enumerator;

import com.example.chayent.samplecustomtab.R;

/**
 * ImageResourceTab.java
 * SampleCustomTab
 * Created by Chayen Tansritrang on 8/10/2018.
 * Copyright © Electronics Extreme Ltd. All rights reserved.
 */
public enum ImageResourceTab {

    PORING(R.drawable.ic_poring_feature, R.drawable.ic_poring_game, R.drawable.ic_poring_social, R.drawable.ic_poring_market),
    POKEMON(R.drawable.ic_pokemon_feature,R.drawable.ic_pokemon_game, R.drawable.ic_pokemon_social, R.drawable.ic_pokemon_market),
    ANIMATION(R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance, R.raw.dino_dance);

    private final int icFeature, icGame, icSocial, icMarket;


    ImageResourceTab(int icFeature, int icGame, int icSocial, int icMarket) {
        this.icFeature = icFeature;
        this.icGame = icGame;
        this.icSocial = icSocial;
        this.icMarket = icMarket;
    }

    public int getIcFeature() {
        return icFeature;
    }

    public int getIcGame() {
        return icGame;
    }

    public int getIcSocial() {
        return icSocial;
    }

    public int getIcMarket() {
        return icMarket;
    }
}

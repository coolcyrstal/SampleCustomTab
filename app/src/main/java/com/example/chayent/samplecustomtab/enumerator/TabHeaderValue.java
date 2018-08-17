package com.example.chayent.samplecustomtab.enumerator;

/**
 * TabHeaderValue.java
 * SampleCustomTab
 * Created by Chayen Tansritrang on 8/9/2018.
 * Copyright © Electronics Extreme Ltd. All rights reserved.
 */
public enum TabHeaderValue {

    FEATURED(0, "Featured"),
    GAME(1, "Game"),
    SOCIAL(2, "Social"),
    MARKET(3, "Market");

    private final int tabIndex;
    private final String tabHeaderText;

    TabHeaderValue(int tabIndex, String tabHeaderText) {
        this.tabIndex = tabIndex;
        this.tabHeaderText = tabHeaderText;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public String getTabHeaderText() {
        return tabHeaderText;
    }
}

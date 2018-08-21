package com.example.chayent.samplecustomtab.enumerator;

/**
 * ToolbarIconIndex.java
 * SampleCustomTab
 * Created by Chayen Tansritrang on 8/21/2018.
 * Copyright © Electronics Extreme Ltd. All rights reserved.
 */
public enum ToolbarIconIndex {

    SEARCH(0),
    NOTIFICATION(1),
    FEEDBACK(2);

    private int iconIndex;

    ToolbarIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public int getIconIndex() {
        return iconIndex;
    }
}

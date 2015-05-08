package com.avene.avene.omilia;

import android.app.Fragment;

/**
 * Created by yamai on 5/8/2015.
 */
public abstract class DrawerItem {
    private String name;


    public DrawerItem(String name) {
        this.name = name;
    }

    public abstract Fragment getFragment();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

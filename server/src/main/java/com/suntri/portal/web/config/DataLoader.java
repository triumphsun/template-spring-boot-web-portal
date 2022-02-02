package com.suntri.portal.web.config;

public abstract class DataLoader {

    public abstract void loadProfileSpecificData();

    public void loadSharedData(){
        // loading nothing currently
    }

    public void loadData(){
        this.loadSharedData();
        this.loadProfileSpecificData();
    }
}

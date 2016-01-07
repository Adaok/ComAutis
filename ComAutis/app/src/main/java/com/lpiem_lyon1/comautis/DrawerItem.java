package com.lpiem_lyon1.comautis;

/**
 * Created by marcoloiodice on 06/01/2016.
 */
public class DrawerItem {

    private String mName;
    private int mIconId;

    public DrawerItem(String mName, int mIconId) {
        this.mIconId = mIconId;
        this.mName = mName;
    }

    public int getmIconId() {
        return mIconId;
    }

    public void setmIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}

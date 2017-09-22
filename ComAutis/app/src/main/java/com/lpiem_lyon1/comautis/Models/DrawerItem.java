package com.lpiem_lyon1.comautis.Models;

/**
 * Created by marcoloiodice on 06/01/2016.
 */
public class DrawerItem {

    private String mName;
    private int mIconId;
    private String mId;
    private TypeItem mTypeItem;

    public DrawerItem(String name, int iconId) {
        this.mIconId = iconId;
        this.mName = name;
    }

    public DrawerItem(String mName, int mIconId, String mId, TypeItem mTypeItem) {
        this.mName = mName;
        this.mIconId = mIconId;
        this.mId = mId;
        this.mTypeItem = mTypeItem;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public TypeItem getmTypeItem() {
        return mTypeItem;
    }

    public void setmTypeItem(TypeItem mTypeItem) {
        this.mTypeItem = mTypeItem;
    }

    public static enum TypeItem {
        OTHER,
        CHILD,
        PAGE
    }
}

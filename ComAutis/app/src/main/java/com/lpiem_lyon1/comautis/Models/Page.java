package com.lpiem_lyon1.comautis.Models;

/**
 * Created by alexislp on 06/01/16.
 */
public class Page extends Model {
    private String mName;
    private int mIsFavorite;
    private String mChildId;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    public String getChildId() {
        return mChildId;
    }

    public void setChildId(String childId) {
        mChildId = childId;
    }
}

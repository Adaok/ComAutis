package com.lpiem_lyon1.comautis.Models;

/**
 * Created by alexislp on 06/01/16.
 */
public class Folder extends Model {
    private String mName;
    private int mIsFavorite;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }
}

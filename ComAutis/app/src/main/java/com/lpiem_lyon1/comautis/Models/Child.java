package com.lpiem_lyon1.comautis.Models;

/**
 * Created by alexislp on 06/01/16.
 */
public class Child extends Model {
    private String mName;
    private String mPicturePath;
    private int mIsFavorite;
    private String mFolderId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPicturePath() {
        return mPicturePath;
    }

    public void setPicturePath(String picturePath) {
        this.mPicturePath = picturePath;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    public String getFolderId() {
        return mFolderId;
    }

    public void setFolderId(String folderId) {
        this.mFolderId = folderId;
    }
}

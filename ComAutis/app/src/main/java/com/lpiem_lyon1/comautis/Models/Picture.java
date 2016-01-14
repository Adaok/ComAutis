package com.lpiem_lyon1.comautis.Models;

import android.graphics.Bitmap;

/**
 * Created by alexislp on 07/01/16.
 */
public class Picture extends Model {
    private String mName;
    private String mPicturePath;
    private int mIsFavorite;
    private String mFolderId;
    private String mPageId;
    private String mOrder;

    private Bitmap mBitmap;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPicturePath() {
        return mPicturePath;
    }

    public void setmPicturePath(String picturePath) {
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

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public String getPageId() {
        return mPageId;
    }

    public void setPageId(String mPageId) {
        this.mPageId = mPageId;
    }
}

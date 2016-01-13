package com.lpiem_lyon1.comautis.Models;

/**
 * Created by marcoloiodice on 13/01/2016.
 */
public class PagePicture extends Model {
    private String mPageId;
    private String mPictureId;
    private String mOrder;

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

    public String getPictureId() {
        return mPictureId;
    }

    public void setPictureId(String mPictureId) {
        this.mPictureId = mPictureId;
    }
}

package com.lpiem_lyon1.comautis.Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexislp on 07/01/16.
 */
public class Picture extends Model implements Parcelable {
    private String mName;
    private String mPicturePath;
    private int mIsFavorite;
    private String mFolderId;
    private String mPageId;
    private String mOrder;

    private Bitmap mBitmap;

    public Picture(){

    }

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

    //Add parcelable aspect.

    public Picture(Parcel inputs) {
        super();
        readFromParcel(inputs);
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>(){
        public Picture createFromParcel(Parcel in){
            return new Picture(in);
        }

        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public void readFromParcel(Parcel inputs){
        mName = inputs.readString();
        mPicturePath = inputs.readString();
        mIsFavorite = inputs.readInt();
        mFolderId = inputs.readString();
        mPageId = inputs.readString();
        mOrder = inputs.readString();
        mBitmap = inputs.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mPicturePath);
        parcel.writeInt(mIsFavorite);
        parcel.writeString(mFolderId);
        parcel.writeString(mPageId);
        parcel.writeString(mOrder);
        parcel.writeParcelable(mBitmap, i);
    }
}

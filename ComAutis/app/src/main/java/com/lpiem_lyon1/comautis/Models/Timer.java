package com.lpiem_lyon1.comautis.Models;

/**
 * Created by alexislp on 06/01/16.
 */
public class Timer extends Model {
    private long mBeginTime;
    private long mEndTime;
    private int mPictureId;

    public long getBeginTime() {
        return mBeginTime;
    }

    public void setBeginTime(long beginTime) {
        this.mBeginTime = beginTime;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public void setEndTime(long endTime) {
        this.mEndTime = endTime;
    }

    public int getPictureId() {
        return mPictureId;
    }

    public void setPictureId(int pictureId) {
        this.mPictureId = pictureId;
    }
}

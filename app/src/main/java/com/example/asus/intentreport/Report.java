package com.example.asus.intentreport;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Asus on 05-10-2016.
 */
public class Report {
    private UUID mId;
    private String mTitle;
    private boolean mResolved;
    private Date mDate;

    public Report(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isResolved() {
        return mResolved;
    }

    public void setResolved(boolean mResolved) {
        this.mResolved = mResolved;
    }


    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}

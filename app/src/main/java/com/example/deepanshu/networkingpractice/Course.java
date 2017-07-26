package com.example.deepanshu.networkingpractice;

/**
 * Created by deepanshu on 26/7/17.
 */

public class Course {

    private int mId;
    private String mTitle;
    private String mName;
    private boolean mIsActive;
    private String mOverview;
    private int mFeeWithTaxes;

    public Course(int id, String title, String name, boolean isActive, String overview, int feeWithTaxes) {
        this.mId = id;
        this.mTitle = title;
        this.mName = name;
        this.mIsActive = isActive;
        this.mOverview = overview;
        this.mFeeWithTaxes = feeWithTaxes;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean isIsActive() {
        return mIsActive;
    }

    public void setIsActive(boolean mIsActive) {
        this.mIsActive = mIsActive;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public int getFeeWithTaxes() {
        return mFeeWithTaxes;
    }

    public void setFeeWithTaxes(int mFeeWithTaxes) {
        this.mFeeWithTaxes = mFeeWithTaxes;
    }
}

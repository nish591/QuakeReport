package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;
    public Earthquake(double Mag, String m_location, long date,String url){
        this.mMag=Mag;
        this.mLocation=m_location;
        this.mTimeInMilliseconds=date;
        this.mUrl=url;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmUrl() {
        return mUrl;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getmLocation() {
        return mLocation;
    }
}

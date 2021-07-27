package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WayPoint implements Parcelable {

    @SerializedName("hint")
    @Expose
    private String hint;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("location")
    @Expose
    private double[] location;


    protected WayPoint(Parcel in) {
        hint = in.readString();
        name = in.readString();
        location = in.createDoubleArray();
    }

    public static final Creator<WayPoint> CREATOR = new Creator<WayPoint>() {
        @Override
        public WayPoint createFromParcel(Parcel in) {
            return new WayPoint(in);
        }

        @Override
        public WayPoint[] newArray(int size) {
            return new WayPoint[size];
        }
    };

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(hint);
        parcel.writeString(name);
        parcel.writeDoubleArray(location);
    }


}

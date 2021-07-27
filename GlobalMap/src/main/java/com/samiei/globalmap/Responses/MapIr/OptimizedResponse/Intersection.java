package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Intersection implements Parcelable {



    @SerializedName("out")
    @Expose
    private int out;

    @SerializedName("entry")
    @Expose
    private boolean[] entry;

    @SerializedName("bearings")
    @Expose
    private int[] bearings;


    @SerializedName("location")
    @Expose
    private double[] location;


    protected Intersection(Parcel in) {
        out = in.readInt();
        entry = in.createBooleanArray();
        bearings = in.createIntArray();
        location = in.createDoubleArray();
    }

    public static final Creator<Intersection> CREATOR = new Creator<Intersection>() {
        @Override
        public Intersection createFromParcel(Parcel in) {
            return new Intersection(in);
        }

        @Override
        public Intersection[] newArray(int size) {
            return new Intersection[size];
        }
    };

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public boolean[] getEntry() {
        return entry;
    }

    public void setEntry(boolean[] entry) {
        this.entry = entry;
    }

    public int[] getBearings() {
        return bearings;
    }

    public void setBearings(int[] bearings) {
        this.bearings = bearings;
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

        parcel.writeInt(out);
        parcel.writeBooleanArray(entry);
        parcel.writeIntArray(bearings);
        parcel.writeDoubleArray(location);
    }


}

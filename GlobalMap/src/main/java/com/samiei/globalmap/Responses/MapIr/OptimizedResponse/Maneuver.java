package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maneuver implements Parcelable {


    @SerializedName("bearing_after")
    @Expose
    private int bearing_after;

    @SerializedName("location")
    @Expose
    private double[] location;


    @SerializedName("bearing_before")
    @Expose
    private int bearing_before;


    @SerializedName("type")
    @Expose
    private String type;


    @SerializedName("modifier")
    @Expose
    private String modifier;


    protected Maneuver(Parcel in) {
        bearing_after = in.readInt();
        location = in.createDoubleArray();
        bearing_before = in.readInt();
        type = in.readString();
        modifier = in.readString();
    }

    public static final Creator<Maneuver> CREATOR = new Creator<Maneuver>() {
        @Override
        public Maneuver createFromParcel(Parcel in) {
            return new Maneuver(in);
        }

        @Override
        public Maneuver[] newArray(int size) {
            return new Maneuver[size];
        }
    };

    public int getBearing_after() {
        return bearing_after;
    }

    public void setBearing_after(int bearing_after) {
        this.bearing_after = bearing_after;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public int getBearing_before() {
        return bearing_before;
    }

    public void setBearing_before(int bearing_before) {
        this.bearing_before = bearing_before;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(bearing_after);
        parcel.writeDoubleArray(location);
        parcel.writeInt(bearing_before);
        parcel.writeString(type);
        parcel.writeString(modifier);
    }


}

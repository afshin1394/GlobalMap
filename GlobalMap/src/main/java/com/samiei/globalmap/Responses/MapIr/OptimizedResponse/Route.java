package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route implements Parcelable {


    @SerializedName("legs")
    @Expose
    private Leg[] legs;

    @SerializedName("distance")
    @Expose
    private double distance;


    @SerializedName("geometry")
    @Expose
    private String geometry;

    @SerializedName("duration")
    @Expose
    private double duration;

    @SerializedName("weight_name")
    @Expose
    private String weight_name;

    @SerializedName("weight")
    @Expose
    private double weight;


    protected Route(Parcel in) {
        legs = in.createTypedArray(Leg.CREATOR);
        distance = in.readDouble();
        geometry = in.readString();
        duration = in.readDouble();
        weight_name = in.readString();
        weight = in.readDouble();
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeTypedArray(legs, i);
        parcel.writeDouble(distance);
        parcel.writeString(geometry);
        parcel.writeDouble(duration);
        parcel.writeString(weight_name);
        parcel.writeDouble(weight);
    }

    public Leg[] getLegs() {
        return legs;
    }

    public void setLegs(Leg[] legs) {
        this.legs = legs;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeight_name() {
        return weight_name;
    }

    public void setWeight_name(String weight_name) {
        this.weight_name = weight_name;
    }


}

package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg implements Parcelable {
    @SerializedName("steps")
    @Expose
    private Step[] steps;


    @SerializedName("distance")
    @Expose
    private double distance;

    @SerializedName("duration")
    @Expose
    private double duration;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("weight")
    @Expose
    private double weight;


    protected Leg(Parcel in) {
        steps = in.createTypedArray(Step.CREATOR);
        distance = in.readDouble();
        duration = in.readDouble();
        summary = in.readString();
        weight = in.readDouble();
    }

    public static final Creator<Leg> CREATOR = new Creator<Leg>() {
        @Override
        public Leg createFromParcel(Parcel in) {
            return new Leg(in);
        }

        @Override
        public Leg[] newArray(int size) {
            return new Leg[size];
        }
    };

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeTypedArray(steps, i);
        parcel.writeDouble(distance);
        parcel.writeDouble(duration);
        parcel.writeString(summary);
        parcel.writeDouble(weight);
    }


}

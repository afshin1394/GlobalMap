package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Step implements Parcelable {


    @SerializedName("intersections")
    @Expose
    private List<Intersection> intersections;

    @SerializedName("driving_side")
    @Expose
    private String driving_side;

    @SerializedName("geometry")
    @Expose
    private String geometry;

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("duration")
    @Expose
    private Double duration;

    @SerializedName("maneuver")
    @Expose
    private Maneuver maneuver;


    @SerializedName("weight")
    @Expose
    private Double weight;


    @SerializedName("distance")
    @Expose
    private Double distance;

    @SerializedName("name")
    @Expose
    private String name;


    protected Step(Parcel in) {
        intersections = in.createTypedArrayList(Intersection.CREATOR);
        driving_side = in.readString();
        geometry = in.readString();
        mode = in.readString();
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readDouble();
        }
        maneuver = in.readParcelable(Maneuver.class.getClassLoader());
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readDouble();
        }
        if (in.readByte() == 0) {
            distance = null;
        } else {
            distance = in.readDouble();
        }
        name = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public String getDriving_side() {
        return driving_side;
    }

    public void setDriving_side(String driving_side) {
        this.driving_side = driving_side;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Maneuver getManeuver() {
        return maneuver;
    }

    public void setManeuver(Maneuver maneuver) {
        this.maneuver = maneuver;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(intersections);
        parcel.writeString(driving_side);
        parcel.writeString(geometry);
        parcel.writeString(mode);
        if (duration == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(duration);
        }
        parcel.writeParcelable(maneuver, i);
        if (weight == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(weight);
        }
        if (distance == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(distance);
        }
        parcel.writeString(name);
    }
}

package com.samiei.globalmap.Models.Direction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapMoveModel implements Parcelable {
    private double distance;
    private double duration;
    private String guidanceSigns;
    private String instruction;
    private List<LatLng> hashRoad;


    public MapMoveModel(double distance, double duration, String guidanceSigns, String instruction, List<LatLng> hashRoad) {
        this.distance = distance;
        this.duration = duration;
        this.guidanceSigns = guidanceSigns;
        this.instruction = instruction;
        this.hashRoad = hashRoad;
    }

    protected MapMoveModel(Parcel in) {
        distance = in.readDouble();
        duration = in.readDouble();
        guidanceSigns = in.readString();
        instruction = in.readString();
        hashRoad = in.createTypedArrayList(LatLng.CREATOR);
    }

    public static final Creator<MapMoveModel> CREATOR = new Creator<MapMoveModel>() {
        @Override
        public MapMoveModel createFromParcel(Parcel in) {
            return new MapMoveModel(in);
        }

        @Override
        public MapMoveModel[] newArray(int size) {
            return new MapMoveModel[size];
        }
    };

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

    public String getGuidanceSigns() {
        return guidanceSigns;
    }

    public void setGuidanceSigns(String guidanceSigns) {
        this.guidanceSigns = guidanceSigns;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<LatLng> getHashRoad() {
        return hashRoad;
    }

    public void setHashRoad(List<LatLng> hashRoad) {
        this.hashRoad = hashRoad;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(distance);
        parcel.writeDouble(duration);
        parcel.writeString(guidanceSigns);
        parcel.writeString(instruction);
        parcel.writeTypedList(hashRoad);
    }
}

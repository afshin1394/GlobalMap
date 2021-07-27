package com.samiei.globalmap.Responses.MapIr.Matrix;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration {
    @SerializedName("destination_index")
    @Expose
    private String destination_index;


    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("origin_index")
    @Expose
    private String origin_index;


    public String getDestination_index() {
        return destination_index;
    }

    public void setDestination_index(String destination_index) {
        this.destination_index = destination_index;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrigin_index() {
        return origin_index;
    }

    public void setOrigin_index(String origin_index) {
        this.origin_index = origin_index;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "destination_index='" + destination_index + '\'' +
                ", duration='" + duration + '\'' +
                ", origin_index='" + origin_index + '\'' +
                '}';
    }
}

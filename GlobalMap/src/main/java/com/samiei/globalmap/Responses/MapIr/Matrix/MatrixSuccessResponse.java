package com.samiei.globalmap.Responses.MapIr.Matrix;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class MatrixSuccessResponse implements Comparable<MatrixSuccessResponse>{

    @SerializedName("destinations")
    @Expose
    private Destination destinations;
    @SerializedName("distance")
    @Expose
    private Distance[] distance;
    @SerializedName("duration")
    @Expose
    private Duration[] durations;
    @SerializedName("origins")
    @Expose
    private Origin origin;


    public Destination getDestinations() {
        return destinations;
    }

    public void setDestinations(Destination destinations) {
        this.destinations = destinations;
    }

    public Distance[] getDistance() {
        return distance;
    }

    public void setDistance(Distance[] distance) {
        this.distance = distance;
    }

    public Duration[] getDurations() {
        return durations;
    }

    public void setDurations(Duration[] durations)
    {
        this.durations = durations;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin)
    {
        this.origin = origin;
    }

    @Override
    public int compareTo(MatrixSuccessResponse matrixSuccessResponse) {
        return 0;
    }

    @Override
    public String toString() {
        return "MatrixSuccessResponse{" +
                "destinations=" + destinations +
                ", distance=" + Arrays.toString(distance) +
                ", durations=" + Arrays.toString(durations) +
                ", origin=" + origin +
                '}';
    }
}

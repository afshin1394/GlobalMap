package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary
{

    @SerializedName("has_time_restrictions")
    @Expose
    private boolean hasTimeRestrictions;
    @SerializedName("max_lon")
    @Expose
    private double maxLon;
    @SerializedName("max_lat")
    @Expose
    private double maxLat;
    @SerializedName("time")
    @Expose
    private double time;
    @SerializedName("length")
    @Expose
    private double length;
    @SerializedName("min_lat")
    @Expose
    private double minLat;
    @SerializedName("min_lon")
    @Expose
    private double minLon;

    public Boolean getHasTimeRestrictions() {
        return hasTimeRestrictions;
    }

    public void setHasTimeRestrictions(Boolean hasTimeRestrictions) {
        this.hasTimeRestrictions = hasTimeRestrictions;
    }

    public Double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(Double maxLon) {
        this.maxLon = maxLon;
    }

    public Double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Double maxLat) {
        this.maxLat = maxLat;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getMinLat() {
        return minLat;
    }

    public void setMinLat(Double minLat) {
        this.minLat = minLat;
    }

    public Double getMinLon() {
        return minLon;
    }

    public void setMinLon(Double minLon) {
        this.minLon = minLon;
    }

}

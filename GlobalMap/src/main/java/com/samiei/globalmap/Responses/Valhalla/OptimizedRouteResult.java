package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptimizedRouteResult
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trip")
    @Expose
    private Trip trip;


    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }


    public Trip getTrip() {
        return trip;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }


}

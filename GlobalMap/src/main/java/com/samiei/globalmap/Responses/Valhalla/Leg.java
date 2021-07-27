package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Leg
{

    @SerializedName("shape")
    @Expose
    private String shape;
    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("maneuvers")
    @Expose
    private ArrayList<Maneuver> maneuvers = null;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public ArrayList<Maneuver> getManeuvers() {
        return maneuvers;
    }

    public void setManeuvers(ArrayList<Maneuver> maneuvers) {
        this.maneuvers = maneuvers;
    }


}

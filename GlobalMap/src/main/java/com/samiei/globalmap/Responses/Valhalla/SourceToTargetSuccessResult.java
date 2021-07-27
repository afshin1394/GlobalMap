package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SourceToTargetSuccessResult
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("targets")
    @Expose
    private ArrayList<ArrayList<TargetModel>> targets = null;
    @SerializedName("sources_to_targets")
    @Expose
    private ArrayList<ArrayList<SourcesToTargetData>> sourcesToTargets = null;
    @SerializedName("sources")
    @Expose
    private List<List<SourceModel>> sources = null;
    @SerializedName("units")
    @Expose
    private String units;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ArrayList<TargetModel>> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<ArrayList<TargetModel>> targets) {
        this.targets = targets;
    }

    public ArrayList<ArrayList<SourcesToTargetData>> getSourcesToTargets() {
        return sourcesToTargets;
    }

    public void setSourcesToTargets(ArrayList<ArrayList<SourcesToTargetData>> sourcesToTargets) {
        this.sourcesToTargets = sourcesToTargets;
    }

    public List<List<SourceModel>> getSources() {
        return sources;
    }

    public void setSources(List<List<SourceModel>> sources) {
        this.sources = sources;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

}

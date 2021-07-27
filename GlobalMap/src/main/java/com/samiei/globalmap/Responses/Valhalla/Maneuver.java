package com.samiei.globalmap.Responses.Valhalla;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Maneuver
{

    @SerializedName("travel_type")
    @Expose
    private String travelType;
    @SerializedName("street_names")
    @Expose
    private ArrayList<String> streetNames = null;
    @SerializedName("verbal_pre_transition_instruction")
    @Expose
    private String verbalPreTransitionInstruction;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("end_shape_index")
    @Expose
    private int endShapeIndex;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("time")
    @Expose
    private float time;
    @SerializedName("verbal_multi_cue")
    @Expose
    private boolean verbalMultiCue;
    @SerializedName("length")
    @Expose
    private double length;
    @SerializedName("begin_shape_index")
    @Expose
    private int beginShapeIndex;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("verbal_transition_alert_instruction")
    @Expose
    private String verbalTransitionAlertInstruction;
    @SerializedName("verbal_post_transition_instruction")
    @Expose
    private String verbalPostTransitionInstruction;
    @SerializedName("sign")
    @Expose
    private Sign sign;
    @SerializedName("roundabout_exit_count")
    @Expose
    private int roundaboutExitCount;

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public ArrayList<String> getStreetNames() {
        return streetNames;
    }

    public void setStreetNames(ArrayList<String> streetNames) {
        this.streetNames = streetNames;
    }

    public String getVerbalPreTransitionInstruction() {
        return verbalPreTransitionInstruction;
    }

    public void setVerbalPreTransitionInstruction(String verbalPreTransitionInstruction) {
        this.verbalPreTransitionInstruction = verbalPreTransitionInstruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getEndShapeIndex() {
        return endShapeIndex;
    }

    public void setEndShapeIndex(int endShapeIndex) {
        this.endShapeIndex = endShapeIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Boolean getVerbalMultiCue() {
        return verbalMultiCue;
    }

    public void setVerbalMultiCue(Boolean verbalMultiCue) {
        this.verbalMultiCue = verbalMultiCue;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getBeginShapeIndex() {
        return beginShapeIndex;
    }

    public void setBeginShapeIndex(int beginShapeIndex) {
        this.beginShapeIndex = beginShapeIndex;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getVerbalTransitionAlertInstruction() {
        return verbalTransitionAlertInstruction;
    }

    public void setVerbalTransitionAlertInstruction(String verbalTransitionAlertInstruction) {
        this.verbalTransitionAlertInstruction = verbalTransitionAlertInstruction;
    }

    public String getVerbalPostTransitionInstruction() {
        return verbalPostTransitionInstruction;
    }

    public void setVerbalPostTransitionInstruction(String verbalPostTransitionInstruction) {
        this.verbalPostTransitionInstruction = verbalPostTransitionInstruction;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public int getRoundaboutExitCount() {
        return roundaboutExitCount;
    }

    public void setRoundaboutExitCount(int roundaboutExitCount) {
        this.roundaboutExitCount = roundaboutExitCount;
    }


    @NonNull
    @Override
    public String toString()
    {
        return "Maneuver{" +
                "travelType='" + travelType + '\'' +
                ", streetNames=" + streetNames +
                ", verbalPreTransitionInstruction='" + verbalPreTransitionInstruction + '\'' +
                ", instruction='" + instruction + '\'' +
                ", endShapeIndex=" + endShapeIndex +
                ", type=" + type +
                ", time=" + time +
                ", verbalMultiCue=" + verbalMultiCue +
                ", length=" + length +
                ", beginShapeIndex=" + beginShapeIndex +
                ", travelMode='" + travelMode + '\'' +
                ", verbalTransitionAlertInstruction='" + verbalTransitionAlertInstruction + '\'' +
                ", verbalPostTransitionInstruction='" + verbalPostTransitionInstruction + '\'' +
                ", sign=" + sign +
                ", roundaboutExitCount=" + roundaboutExitCount +
                '}';
    }
}

package com.samiei.globalmap.Responses.Valhalla;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourcesToTargetData implements Comparable<SourcesToTargetData>
{

    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("to_index")
    @Expose
    private int toIndex;
    @SerializedName("from_index")
    @Expose
    private int fromIndex;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }


    @Override
    public int compareTo(@NonNull SourcesToTargetData sourcesToTargetData)
    {
        if (this.time > sourcesToTargetData.getTime())
        {
            return 1;
        }
        else if (this.time < sourcesToTargetData.getTime())
        {
            return -1;
        }
        else if (this.time == sourcesToTargetData.getTime())
        {
            if (this.distance > sourcesToTargetData.getDistance())
            {
                return 1;
            }
            else if (this.distance < sourcesToTargetData.getDistance())
            {
                return -1;
            }
            else if (this.distance == sourcesToTargetData.getDistance())
            {
                return 0;
            }
        }
        /*if (this.distance > sourcesToTargetData.getDistance())
        {
            return 1;
        }
        else if (this.distance < sourcesToTargetData.getDistance())
        {
            return -1;
        }
        else if (this.distance == sourcesToTargetData.getDistance())
        {
            if (this.time > sourcesToTargetData.getTime())
            {
                return 1;
            }
            else if (this.time < sourcesToTargetData.getTime())
            {
                return -1;
            }
            else if (this.time == sourcesToTargetData.getTime())
            {
                return 0;
            }
        }*/
        return 0;
    }

}

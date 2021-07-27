package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sign
{

    @SerializedName("exit_toward_elements")
    @Expose
    private ArrayList<ExitTowardElement> exitTowardElements = null;

    public ArrayList<ExitTowardElement> getExitTowardElements() {
        return exitTowardElements;
    }

    public void setExitTowardElements(ArrayList<ExitTowardElement> exitTowardElements) {
        this.exitTowardElements = exitTowardElements;
    }


}

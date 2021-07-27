package com.samiei.globalmap.Models.Matrix;

import com.google.android.gms.maps.model.LatLng;

public class MatrixParam {

    private int id;
    private LatLng latLngs;




    public MatrixParam(int id, LatLng latLng) {
        this.id = id;
        this.latLngs = latLng;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLatLngs() {
        return latLngs;
    }

    public void setLatLngs(LatLng latLngs) {
        this.latLngs = latLngs;
    }
}

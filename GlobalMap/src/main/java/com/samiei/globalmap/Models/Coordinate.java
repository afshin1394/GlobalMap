package com.samiei.globalmap.Models;

public class Coordinate<T extends Object>  {

    private T latitude;
    private T longitude;
    private T altitude;

    public Coordinate(T latitude, T longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public T getLatitude() {
        return latitude;
    }

    public void setLatitude(T latitude) {
        this.latitude = latitude;
    }

    public T getLongitude() {
        return longitude;
    }

    public void setLongitude(T longitude) {
        this.longitude = longitude;
    }

    public T getAltitude() {
        return altitude;
    }

    public void setAltitude(T altitude) {
        this.altitude = altitude;
    }
}

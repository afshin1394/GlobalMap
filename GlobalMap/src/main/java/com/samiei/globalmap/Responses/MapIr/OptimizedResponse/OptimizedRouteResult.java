package com.samiei.globalmap.Responses.MapIr.OptimizedResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptimizedRouteResult implements Parcelable {

    private int id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("routes")
    @Expose
    private Route[] routes;


    @SerializedName("waypoints")
    @Expose
    private WayPoint[] wayPoints;


    @SerializedName("message")
    @Expose
    private String message;


    protected OptimizedRouteResult(Parcel in) {
        id = in.readInt();
        code = in.readString();
        routes = in.createTypedArray(Route.CREATOR);
        wayPoints = in.createTypedArray(WayPoint.CREATOR);
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeTypedArray(routes, flags);
        dest.writeTypedArray(wayPoints, flags);
        dest.writeString(message);
    }

    public static final Creator<OptimizedRouteResult> CREATOR = new Creator<OptimizedRouteResult>() {
        @Override
        public OptimizedRouteResult createFromParcel(Parcel in) {
            return new OptimizedRouteResult(in);
        }

        @Override
        public OptimizedRouteResult[] newArray(int size) {
            return new OptimizedRouteResult[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    public WayPoint[] getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(WayPoint[] wayPoints) {
        this.wayPoints = wayPoints;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OptimizedRouteResult{" +
                "id='" + id + '\'' +
                "code='" + code + '\'' +
                ", routes=" + routes +
                ", wayPoints=" + wayPoints +
                ", message='" + message + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }


}

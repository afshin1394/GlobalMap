package com.samiei.globalmap.Models.Direction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MapDirectionModel implements Parcelable {
    private Builder builder;
    private String routeId;
    private String hashRoad;
    private double overAllDuration;
    private double overAllDistance;
    private LatLng startingPoint;
    private LatLng endingPoint;
    ArrayList< MapMoveModel > movements;
    public MapDirectionModel(final Builder builder)
    {

        this.routeId = builder.routeId;
        this.hashRoad =builder.hashRoad;
        this.overAllDistance =builder.overAllDistance;
        this.overAllDuration =builder.overAllDuration;
        this.startingPoint =builder.startingPoint;
        this.endingPoint =builder.endingPoint;
        this.movements =builder.movements;

    }

    protected MapDirectionModel(Parcel in) {

        routeId = in.readString();
        hashRoad = in.readString();
        overAllDuration = in.readDouble();
        overAllDistance = in.readDouble();
        startingPoint = in.readParcelable(LatLng.class.getClassLoader());
        endingPoint = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<MapDirectionModel> CREATOR = new Creator<MapDirectionModel>() {
        @Override
        public MapDirectionModel createFromParcel(Parcel in) {
            return new MapDirectionModel(in);
        }

        @Override
        public MapDirectionModel[] newArray(int size) {
            return new MapDirectionModel[size];
        }
    };

    public Builder getBuilder() {
        return builder;
    }

    public double getOverAllDuration() {
        return overAllDuration;
    }

    public double getOverAllDistance() {
        return overAllDistance;
    }

    public LatLng getStartingPoint() {
        return startingPoint;
    }

    public LatLng getEndingPoint() {
        return endingPoint;
    }

    public ArrayList<MapMoveModel> getMovements() {
        return movements;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getHashRoad() {
        return hashRoad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(movements);
        parcel.writeString(routeId);
        parcel.writeString(hashRoad);
        parcel.writeDouble(overAllDuration);
        parcel.writeDouble(overAllDistance);
        parcel.writeParcelable(startingPoint, i);
        parcel.writeParcelable(endingPoint, i);
    }

    public static class Builder {
        private String routeId;
        private String hashRoad;
        private double overAllDuration;
        private double overAllDistance;
        private LatLng startingPoint;
        private LatLng endingPoint;
        ArrayList< MapMoveModel > movements;


        public Builder setRouteId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder setHashRoad(String hashRoad) {
            this.hashRoad = hashRoad;
            return this;
        }

        public Builder setOverAllDuration(double overAllDuration) {
            this.overAllDuration = overAllDuration;
            return this;
        }

        public Builder setOverAllDistance(double overAllDistance) {
            this.overAllDistance = overAllDistance;
            return this;
        }

        public Builder setStartingPoint(LatLng startingPoint) {
            this.startingPoint = startingPoint;
            return this;
        }

        public Builder setEndingPoint(LatLng endingPoint) {
            this.endingPoint = endingPoint;
            return this;
        }

        public Builder setMovements(ArrayList<MapMoveModel> movements) {
            this.movements = movements;
            return this;

        }

        public MapDirectionModel create() {
            return new MapDirectionModel(this);
        }
    }


    @Override
    public String toString() {
        return "MapDirectionModel{" +
                "builder=" + builder +
                ", routeId='" + routeId + '\'' +
                ", hashRoad='" + hashRoad + '\'' +
                ", overAllDuration=" + overAllDuration +
                ", overAllDistance=" + overAllDistance +
                ", startingPoint=" + startingPoint +
                ", endingPoint=" + endingPoint +
                ", movements=" + movements +
                '}';
    }
}

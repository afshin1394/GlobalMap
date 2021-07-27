package com.samiei.globalmap.Models;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class MapLineModel extends MapObjectModel {

    private ArrayList<GeoPoint> geoPoints;
    private Float lineWidth;
    private Float lineGapWidth;
    private int lineColor;
    private Float[] lineDashArray;





    public MapLineModel(String groupKey,String source_id,String layer_id,Builder builder) {
        super(groupKey,layer_id,source_id);
        this.geoPoints = builder.geoPoints;
        this.lineWidth = builder.lineWidth;
        this.lineGapWidth = builder.lineGapWidth;
        this.lineColor = builder.lineColor;
        this.lineDashArray = builder.lineDashArray;
    }

    public MapLineModel(String id, String layer_id, String source_id, ArrayList<GeoPoint> geoPoints, Float lineWidth, Float lineGapWidth, int lineColor, Float[] lineDashArray) {
        super(id, layer_id, source_id);
        this.geoPoints = geoPoints;
        this.lineWidth = lineWidth;
        this.lineGapWidth = lineGapWidth;
        this.lineColor = lineColor;
        this.lineDashArray = lineDashArray;
    }

    public ArrayList<GeoPoint> getGeoPoints() {
        return geoPoints;
    }

    public void setGeoPoints(ArrayList<GeoPoint> geoPoints) {
        this.geoPoints = geoPoints;
    }

    public Float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Float getLineGapWidth() {
        return lineGapWidth;
    }

    public void setLineGapWidth(Float lineGapWidth) {
        this.lineGapWidth = lineGapWidth;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public Float[] getLineDashArray() {
        return lineDashArray;
    }

    public void setLineDashArray(Float[] lineDashArray) {
        this.lineDashArray = lineDashArray;
    }

    public static class Builder {
        private String groupKey;
        private String layer_id;
        private String source_id;
        private ArrayList<GeoPoint> geoPoints;
        private Float lineWidth;
        private Float lineGapWidth;
        private int lineColor;
        private Float[] lineDashArray;


        public Builder setLayer_id(String layer_id) {
            this.layer_id = layer_id;
            return this;
        }

        public Builder setSource_id(String source_id) {
            this.source_id = source_id;
            return this;
        }



        public Builder setGeoPoints(ArrayList<GeoPoint> geoPoints) {
            this.geoPoints = geoPoints;
            return this;
        }

        public Builder setLineWidth(Float lineWidth) {
            this.lineWidth = lineWidth;
            return this;
        }

        public Builder setLineGapWidth(Float lineGapWidth) {
            this.lineGapWidth = lineGapWidth;
            return this;
        }

        public Builder setLineColor(int lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        public Builder setLineDashArray(Float[] lineDashArray) {
            this.lineDashArray = lineDashArray;
            return this;
        }



        public Builder setGroupKey(String groupKey) {
            this.groupKey = groupKey;
            return this;
        }

        public MapLineModel create() {
            MapLineModel mapLineModel = new MapLineModel(groupKey,layer_id,source_id,this);
            return mapLineModel;
        }
    }
}

package com.samiei.globalmap.Models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class CirclePolygonModel extends PolygonModel {
    private Float radius;
    private LatLng center;



    public CirclePolygonModel(Integer fillColor, Integer strokeColor, Float strokeWidth, String title, Float fillOpacity, String layer_id, String source_id, Float radius, LatLng center,final Builder builder)
    {
        super(fillColor, strokeColor, strokeWidth, title, fillOpacity, layer_id, source_id);
        this.radius = builder.radius;
        this.center = builder.center;
    }



    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    @Override
    public String toString() {
        return "CirclePolygonModel{" +
                "radius=" + radius +
                ", center=" + center +
                '}';
    }

    public static class Builder {
       private Float radius;
       private LatLng center;

       private Integer fillColor;
       private Integer strokeColor;
       private Float strokeWidth;
       private String title;
       private Float fillOpacity;
       private List<LatLng> latLng;
       private String layer_id;
       private String source_id;
       private String below_layer;
       private String top_layer;
       private boolean hasInfo;
       private String group_key;


        public Builder setRadius(Float radius)
        {
            this.radius = radius;
            return this;
        }

        public Builder setFillColor(Integer fillColor)
        {
            this.fillColor = fillColor;
            return this;
        }


        public Builder setStrokeColor(Integer strokeColor)
        {
            this.strokeColor = strokeColor;
            return this;
        }


        public Builder setStrokeWidth(Float strokeWidth)
        {
            this.strokeWidth = strokeWidth;
            return this;
        }


        public Builder setTitle(String title)
        {
            this.title = title;
            return this;
        }

        public Builder setFillOpacity(Float fillOpacity)
        {
            this.fillOpacity = fillOpacity;
            return this;
        }

        public Builder setLayer_id(String layer_id)
        {
            this.layer_id = layer_id;
            return this;
        }

        public Builder setSource_id(String source_id)
        {
            this.source_id = source_id;
            return this;
        }

        public Builder setGroup_key(String group_key)
        {
            this.group_key = group_key;
            return this;
        }
        public Builder setCenter(LatLng center)
        {
            this.center = center;
            return this;
        }

        public Float getRadius() {
            return radius;
        }

        public LatLng getCenter() {
            return center;
        }

        public Integer getFillColor() {
            return fillColor;
        }

        public Integer getStrokeColor() {
            return strokeColor;
        }

        public Float getStrokeWidth() {
            return strokeWidth;
        }

        public String getTitle() {
            return title;
        }

        public Float getFillOpacity() {
            return fillOpacity;
        }

        public List<LatLng> getLatLng() {
            return latLng;
        }

        public String getLayer_id() {
            return layer_id;
        }

        public String getSource_id() {
            return source_id;
        }

        public String getBelow_layer() {
            return below_layer;
        }

        public String getTop_layer() {
            return top_layer;
        }

        public boolean isHasInfo() {
            return hasInfo;
        }


        public CirclePolygonModel create() {
            return new CirclePolygonModel(fillColor,strokeColor,strokeWidth,title,fillOpacity,layer_id,source_id,radius,center,this);
        }
    }


}

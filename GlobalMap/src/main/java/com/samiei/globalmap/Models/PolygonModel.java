package com.samiei.globalmap.Models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class PolygonModel extends MapObjectModel{

    private Integer fillColor;
    private Integer strokeColor;
    private Float strokeWidth;
    private Float fillOpacity;
    private List<LatLng> latLng;
    private String below_layer;
    private String top_layer;
    private boolean hasBorders;



    public PolygonModel( final Builder builder){
        super(builder. group_key , builder.layer_id, builder.source_id);
        this.fillColor =builder. fillColor;
        this.strokeColor =builder. strokeColor;
        this.strokeWidth = builder. strokeWidth;
        this.latLng = builder.latLng;
        this.fillColor = builder.fillColor;
        this.strokeColor = builder.strokeColor;
        this.strokeWidth = builder.strokeWidth;
        this.fillOpacity = builder.fillOpacity;
        this.hasBorders = builder.hasBorders;
    }

    public PolygonModel(Integer fillColor, Integer strokeColor, Float strokeWidth, String title, Float fillOpacity, String layer_id, String source_id) {
        super(layer_id, source_id);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.fillOpacity = fillOpacity;

    }

    public boolean hasBorders() {
        return hasBorders;
    }

    public Float getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(Float fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public Integer getFillColor() {
        return fillColor;
    }

    public void setFillColor(Integer fillColor) {
        this.fillColor = fillColor;
    }

    public Integer getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Integer strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public List<LatLng> getLatLngList() {
        return latLng;
    }

    public void setLatLng(List<LatLng> latLng) {
        this.latLng = latLng;
    }



    public String getBelow_layer() {
        return below_layer;
    }

    public void setBelow_layer(String below_layer) {
        this.below_layer = below_layer;
    }

    public String getTop_layer() {
        return top_layer;
    }

    public void setTop_layer(String top_layer) {
        this.top_layer = top_layer;
    }

    public static class Builder {
        private Integer fillColor;
        private Integer strokeColor;
        private Float strokeWidth;

        private Float fillOpacity;
        private List<LatLng> latLng;
        private String layer_id;
        private String source_id;
        private boolean hasInfo;
        private boolean hasBorders;
        private String group_key;
        private String image_id;
        private boolean hasInfoWindow;



        public Builder setImage_id(String image_id) {
            this.image_id = image_id;
            return this;
        }

        public Builder setSource_id(String source_id) {
            this.source_id = source_id;
            return this;
        }




        public Builder setLatLng(List<LatLng> latLng) {
            this.latLng = latLng;
            return this;
        }








        public Builder setHasInfoWindow(boolean hasInfoWindow) {
            this.hasInfoWindow = hasInfoWindow;
            return this;
        }

        public Builder setGroup_key(String group_key) {
            this.group_key = group_key;
            return this;
        }

        public Builder setLayer_id(String layer_id) {
            this.layer_id = layer_id;
            return this;
        }


        public Builder setFillColor(Integer fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public Builder setStrokeColor(Integer strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        public Builder setStrokeWidth(Float strokeWidth) {
            this.strokeWidth = strokeWidth;
            return this;
        }

        public Builder setFillOpacity(Float fillOpacity) {
            this.fillOpacity = fillOpacity;
            return this;
        }

        public Builder setHasInfo(boolean hasInfo) {
            this.hasInfo = hasInfo;
            return this;
        }
        public Builder setHasBorders(boolean hasBorders) {
            this.hasBorders = hasBorders;
            return this;
        }

        public PolygonModel create() {
            return new PolygonModel(this);
        }
    }
}

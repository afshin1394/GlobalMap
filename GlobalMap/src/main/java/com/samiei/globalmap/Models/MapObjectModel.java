package com.samiei.globalmap.Models;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;

import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;

public class MapObjectModel {

    private int id;
    private String layer_id;
    private String image_id;
    private String source_id;
    private String titleId;
    private String Title;
    private LatLng latLng;
    private Drawable drawable;
    private Float ObjectSize;
    private boolean visible;
    private boolean draggable;
    private boolean Clickable;
    private boolean focusable;
    private float zoomScale;
    private SymbolManager manager;
    private Symbol symbol;
    private boolean hasInfoWindow;
    private String group_key;
    private Overlay overlay;



    public MapObjectModel(String layer_id, String source_id) {
        this.layer_id = layer_id;
        this.source_id = source_id;
    }

    private MapObjectModel(final Builder builder) {

        this.layer_id = builder.layer_id;
        this.source_id = builder.source_id;
        this.group_key = builder.group_key;
        this.image_id =builder. image_id;
        this.source_id =builder. source_id;
        this.latLng =builder. latLng;
        this.drawable =builder. drawable;
        this.ObjectSize =builder. objectSize;
    }

    public MapObjectModel(String group_key, String layer_id, String source_id) {
        this.group_key =  group_key;
        this.layer_id = layer_id;
        this.source_id = source_id;
    }

    public MapObjectModel(int id, String source_id, String layer_id, String image_id, LatLng latLng, Drawable drawable, Float objectSize) {
        this.id = id;
        this.layer_id = layer_id;
        this.image_id = image_id;
        this.source_id = source_id;
        this.latLng = latLng;
        this.drawable = drawable;
        ObjectSize = objectSize;
    }

    public Overlay getOverlay() {
        return overlay;
    }

    public void setOverlay(Overlay overlay) {
        this.overlay = overlay;
    }

    public int getId() {
        return id;
    }

    public String getLayer_id() {
        return layer_id;
    }

    public void setLayer_id(String layer_id) {
        this.layer_id = layer_id;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public float getZoomScale() {
        return zoomScale;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setZoomScale(float zoomScale) {
        this.zoomScale = zoomScale;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public SymbolManager getManager() {
        return manager;
    }

    public void setManager(SymbolManager manager) {
        this.manager = manager;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Float getObjectSize() {
        return ObjectSize;
    }

    public void setObjectSize(Float objectSize) {
        ObjectSize = objectSize;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isClickable() {
        return Clickable;
    }

    public void setClickable(boolean clickable) {
        Clickable = clickable;
    }

    public boolean isFocusable() {
        return focusable;
    }

    public void setFocusable(boolean focusable) {
        this.focusable = focusable;
    }

    public boolean hasInfoWindow() {
        return hasInfoWindow;
    }

    public void setHasInfoWindow(boolean hasInfoWindow) {
        this.hasInfoWindow = hasInfoWindow;
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public interface MapObjectEvents {
        void onMapObjectClick();
    }

    public String getGroup_key() {
        return group_key;
    }

    public void setGroup_key(String group_key) {
        this.group_key = group_key;
    }


   public static class Builder {
        private int id;
        private String layer_id;
        private String image_id;
        private String source_id;
        private String titleId;
        private String Title;
        private LatLng latLng;
        private Drawable drawable;
        private Float objectSize;
        private boolean visible;
        private boolean draggable;
        private boolean Clickable;
        private boolean focusable;
        private float zoomScale;
        private SymbolManager manager;
        private Symbol symbol;
        private boolean hasInfoWindow;
        private String group_key;
        private Marker marker;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setImage_id(String image_id) {
            this.image_id = image_id;
            return this;
        }

        public Builder setSource_id(String source_id) {
            this.source_id = source_id;
            return this;
        }

        public Builder setTitleId(String titleId) {
            this.titleId = titleId;
            return this;
        }

        public Builder setTitle(String title) {
            Title = title;
            return this;
        }

        public Builder setLatLng(LatLng latLng) {
            this.latLng = latLng;
            return this;
        }

        public Builder setDrawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setObjectSize(Float objectSize) {
            this.objectSize = objectSize;
            return this;
        }

        public Builder setVisible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder setDraggable(boolean draggable) {
            this.draggable = draggable;
            return this;
        }

        public Builder setClickable(boolean clickable) {
            Clickable = clickable;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            this.focusable = focusable;
            return this;
        }

        public Builder setZoomScale(float zoomScale) {
            this.zoomScale = zoomScale;
            return this;
        }

        public Builder setManager(SymbolManager manager) {
            this.manager = manager;
            return this;
        }

        public Builder setSymbol(Symbol symbol) {
            this.symbol = symbol;
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



       public MapObjectModel create() {
            return new MapObjectModel(this);
        }
    }
}

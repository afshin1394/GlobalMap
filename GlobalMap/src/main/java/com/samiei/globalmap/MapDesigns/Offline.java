package com.samiei.globalmap.MapDesigns;


import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.samiei.globalmap.Interfaces.IMapClickEvents;
import com.samiei.globalmap.MapInstructor;
import com.samiei.globalmap.Models.CirclePolygonModel;
import com.samiei.globalmap.Models.MapLineModel;
import com.samiei.globalmap.Models.MapObjectModel;
import com.samiei.globalmap.Models.PolygonModel;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.HashMap;

import ir.map.mapsdk.BuildConfig;

public class Offline extends MapInstructor {


    private  final String TAG = this.getClass().getSimpleName();
    Context context;
    MapView mapView;

    HashMap<String, ArrayList<? extends Overlay>> allMarkers = new HashMap<>();
    private String tileSourceName;
    private int tileMinZoom;
    private int tileMaxZoom;

    /**
     * instantiating our map for the first time
     *
     * @param context
     * @param mapView
     * @param showTarh
     */
    public Offline(Context context, MapView mapView, boolean showTarh) {
        super(context);

        this.context = context;
        this.mapView = mapView;
        mapView.setMultiTouchControls(true);

            mapView.getTileProvider().clearTileCache();
            mapView.setMinZoomLevel(15d);
            mapView.setMaxZoomLevel(22d);
            ITileSource tileSource = new XYTileSource(tileSourceName,tileMinZoom, tileMaxZoom, 256, ".png", new String[0]);
            mapView.setTileSource(tileSource);
            mapView.setUseDataConnection(false);


        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        if (showTarh) {
            drawTarhAsli();
            drawTarhZojoFard();
        }

    }

    /**
     * showCurrent Location
     *
     * @param mapObjectModel
     */
    @Override
    public void showCurrentLocation(MapObjectModel mapObjectModel) {
        Marker markerCurrentLocation = new Marker(mapView);
        markerCurrentLocation.setPosition(new GeoPoint(mapObjectModel.getLatLng().latitude, mapObjectModel.getLatLng().longitude));
        markerCurrentLocation.setIcon(mapObjectModel.getDrawable());
        markerCurrentLocation.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(markerCurrentLocation);
        mapView.invalidate();
        if (!mapObjectModel.hasInfoWindow()) {
            markerCurrentLocation.setInfoWindow(null);
        }
        ArrayList<Overlay> overlays = new ArrayList<>();
        overlays.add(markerCurrentLocation);


        allMarkers.put(mapObjectModel.getGroup_key(), overlays);
    }

    /**
     * draw circle polygon
     * with Turf interface
     *
     * @param circlePolygonModel
     */
    @Override
    public void drawCirclePolygon(CirclePolygonModel circlePolygonModel) {
        Polygon circle = new Polygon(mapView);
        if (!circlePolygonModel.hasInfoWindow()) {
            circle.setInfoWindow(null);
        }
        circle.setPoints(Polygon.pointsAsCircle(new GeoPoint(circlePolygonModel.getCenter().latitude, circlePolygonModel.getCenter().longitude), circlePolygonModel.getRadius()));
        circle.setFillColor(circlePolygonModel.getFillColor());
        circle.setStrokeColor(circlePolygonModel.getStrokeColor());
        circle.setStrokeWidth(circlePolygonModel.getStrokeWidth());
        mapView.getOverlays().add(circle);
        mapView.invalidate();


        ArrayList<Overlay> overlays = new ArrayList<>();
        overlays.add(circle);
        allMarkers.put(circlePolygonModel.getGroup_key(), overlays);
    }

    /**
     * remove existing features on the map
     * base on the group key we set for initialization
     *
     * @param groupKey
     * @return
     */
    @Override
    public boolean removeExistingFeatures(String groupKey) {
        if (allMarkers.containsKey(groupKey)) {
            ArrayList<? extends Overlay> overlays = allMarkers.get(groupKey);
            if (overlays != null) {
                if (overlays.size() > 0) {
                    for (Overlay overlay : overlays) {
                        if (mapView.getOverlays().contains(overlay))
                            mapView.getOverlays().remove(overlay);
                        mapView.invalidate();
                    }
                    allMarkers.remove(groupKey);

                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * draw polyline
     *
     * @param mapLineModel
     */
    @Override
    public void drawLinePolygon(MapLineModel mapLineModel) {

        Polyline polyline = new Polyline();
        polyline.setPoints(mapLineModel.getGeoPoints());
        polyline.setColor(mapLineModel.getLineColor());
        mapView.getOverlayManager().add(polyline);
        if (!mapLineModel.hasInfoWindow())
            polyline.setInfoWindow(null);


        ArrayList<Overlay> overlays = new ArrayList<>();
        overlays.add(polyline);
        allMarkers.put(mapLineModel.getGroup_key(), overlays);
        mapView.invalidate();
    }

    /**
     * show group of destinations
     *
     * @param mapObjectModels
     * @param groupMarkersId
     */
    @Override
    public void addGroupLocationLayer(ArrayList<MapObjectModel> mapObjectModels, String groupMarkersId) {
        ArrayList<Marker> destMarkers = new ArrayList<>();
        for (MapObjectModel mapObjectModel : mapObjectModels) {
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(mapObjectModel.getLatLng().latitude, mapObjectModel.getLatLng().longitude));
            marker.setIcon(mapObjectModel.getDrawable());
            marker.setTitle(mapObjectModel.getTitle());
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            if (!mapObjectModel.hasInfoWindow()) {
                marker.setInfoWindow(null);
            }


            Log.i("adding", "showDestinationPositions: " + marker.getPosition());
            if (mapView.getOverlays().contains(marker))
                mapView.getOverlays().remove(marker);

            destMarkers.add(marker);
            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();
        allMarkers.put(groupMarkersId, destMarkers);


    }

    /**
     * show single destination
     *
     * @param mapObjectModel
     */
    @Override
    public void addSingleLocationLayer(MapObjectModel mapObjectModel) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(mapObjectModel.getLatLng().latitude, mapObjectModel.getLatLng().longitude));
        marker.setIcon(mapObjectModel.getDrawable());
        marker.setTitle(mapObjectModel.getTitle());
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        if (!mapObjectModel.hasInfoWindow()) {
            marker.setInfoWindow(null);
        }



        mapView.invalidate();
        mapView.getOverlays().add(marker);
        ArrayList<Marker> markers = new ArrayList<>();
        markers.add(marker);
        allMarkers.put(mapObjectModel.getGroup_key(), markers);
    }


    private ArrayList<? extends Overlay> getMarkersById(String groupMarkers) {
        return allMarkers.get(groupMarkers);
    }



    /**
     * draw multi sided polygon
     *
     * @param polygonModels
     */
    @Override
    public void drawSellPolygon(ArrayList<PolygonModel> polygonModels) {
        for (PolygonModel polygonModel : polygonModels) {
            Polygon polygon = new Polygon();
            try {

                polygon.setFillColor(polygonModel.getFillColor());
                polygon.setStrokeColor(polygonModel.getStrokeColor());
                polygon.setStrokeWidth(polygonModel.getStrokeWidth());
                if (!polygonModel.hasInfoWindow()) {
                    polygon.setInfoWindow(null);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                throw  new RuntimeException("stub");
            }
            ArrayList<GeoPoint> geoPoints = new ArrayList<>();

            for (LatLng latLng : polygonModel.getLatLngList()) {
                geoPoints.add(new GeoPoint(latLng.latitude, latLng.longitude));
            }

            polygon.setPoints(geoPoints);
            polygon.setTitle(polygonModel.getTitle());

            polygonModel.setOverlay(polygon);

            mapView.getOverlayManager().add(polygon);
            mapView.invalidate();

            ArrayList<Overlay> overlays = new ArrayList<>();
            overlays.add(polygon);
            allMarkers.put(polygonModels.get(0).getGroup_key(), overlays);
        }
    }


    /**
     * draw starting location
     *
     * @param mapObjectModel
     */
    @Override
    public void setStartLocation(MapObjectModel mapObjectModel)
    {
        MyLocationNewOverlay myLocationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context), mapView);
        myLocationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(myLocationNewOverlay);


        Marker startMarker = new Marker(mapView);
        startMarker.setIcon(mapObjectModel.getDrawable());
        startMarker.setPosition(new GeoPoint(mapObjectModel.getLatLng().latitude, mapObjectModel.getLatLng().longitude));
        if (mapObjectModel.hasInfoWindow())
            startMarker.setInfoWindow(null);


        mapObjectModel.setOverlay(startMarker);

        mapView.getOverlays().add(startMarker);
        mapView.invalidate();


        ArrayList<Marker> startMarkers = new ArrayList<>();
        startMarkers.add(startMarker);
        allMarkers.put(mapObjectModel.getGroup_key(), startMarkers);
    }

    @Override
    public LatLng convertGeoPointToLatLng(GeoPoint geoPoint) {
        return null;
    }

    @Override
    public void moveCameraToSpecificPosition(LatLng latLng) {

        IMapController mapController = new MapController(this.mapView);
        GeoPoint geoPoint = new GeoPoint(latLng.latitude, latLng.longitude);
        mapController.setCenter(geoPoint);
    }

    @Override
    public void zoomCameraToSpecificPosition(GeoPoint geoPoint, int zoomScale) {
        IMapController mapController = new MapController(this.mapView);
        mapController.setCenter(geoPoint);
        mapController.setZoom(zoomScale);
        mapController.animateTo(geoPoint);


    }

    /**
     * on Map symbols and icons click listener//
     *
     * @param groupMarkers    layer_id
     * @param iMapClickEvents listener
     *                        <p>
     *                        USE THIS FOR SYMBOLS
     *                        AND MAP
     */
    @Override
    public void onMapContentClickListener(String groupMarkers, IMapClickEvents iMapClickEvents)
    {

        mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                ArrayList <? extends Overlay> items = getMarkersById(groupMarkers);
                int i = findLayers(p, items);

                if (i == -1)
                    iMapClickEvents.onOtherItemsClick();
                else
                    iMapClickEvents.onMarkSingleTap(i,items.get(i));


                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                ArrayList <? extends Overlay> items = getMarkersById(groupMarkers);
                int i = findLayers(p, items);

                if (i == -1)
                    iMapClickEvents.onOtherItemsClick();
                else
                    iMapClickEvents.onMarkLongTap(i,items.get(i));
                return false;
            }
        }));

    }

    private double scaleTouchAccuracyBaseOnZoomScale()
    {
        Log.i(TAG, "scaleTouchAccuracyBaseOnZoomScale: "+mapView.getZoomLevelDouble());


        if ((int) mapView.getZoomLevelDouble()<11){
            return 400;
        }
        switch ((int) mapView.getZoomLevelDouble())
        {

            case 11:
                return 300;
            case 12:
                return 200;
            case 13:
                return 150;
            case 14:
            case 15:
                return 20;
            case 16:
                return 8;
            case 18:
                return 6;
            case 17:
                return 4;
            case 19:
            case 20:
                return 1.5;
            default:
                return 0.25;
        }






    }

    private int findLayers(GeoPoint p, ArrayList<? extends Overlay> items) {
        HashMap<Integer, Overlay> foundedLayers =new HashMap<>();

        for (int i = 0; i < items.size(); i++) {

            Log.d(TAG, "findLayers: longitude"+items.get(i).getBounds().getCenterLongitude()+"latitude:"+items.get(i).getBounds().getCenterLatitude());
            Log.d(TAG, "findLayers: touchPoint longitude"+p.getLongitude()+"latitude"+p.getLatitude());
            double scaledAccuracy = scaleTouchAccuracyBaseOnZoomScale();
            if (items.get(i).getBounds().getCenterLatitude() - TOUCH_ACCURACY() * scaledAccuracy <= p.getLatitude()
                    && items.get(i).getBounds().getCenterLatitude() + TOUCH_ACCURACY() * scaledAccuracy >= p.getLatitude()
                    && items.get(i).getBounds().getCenterLongitude() - TOUCH_ACCURACY() * scaledAccuracy <= p.getLongitude()
                    && items.get(i).getBounds().getCenterLongitude() + TOUCH_ACCURACY() * scaledAccuracy >= p.getLongitude()) {
                Log.d(TAG, "findLayers: accepted");
                foundedLayers.put(i,items.get(i));
                Log.i(TAG, "findLayers: "+foundedLayers.size());

            }
        }

        if (foundedLayers.size()== 0)
            return -1;
        else
            return findClosest(foundedLayers,p);

    }

    private int findClosest(HashMap<Integer,Overlay> foundedLayers,GeoPoint p) {
        double minimum=100000;
        int minimumIndex = -1;
        for (Integer integer : foundedLayers.keySet()) {

            Overlay item = foundedLayers.get(integer);
            if (calculateDistanceBetweenPointsWithHypot(item.getBounds().getCenterLatitude(),item.getBounds().getCenterLongitude(),p.getLatitude(),p.getLongitude())<minimum)
            {
                minimum = calculateDistanceBetweenPointsWithHypot(item.getBounds().getCenterLatitude(),item.getBounds().getCenterLongitude(),p.getLatitude(),p.getLongitude());
                minimumIndex = integer;
            }

        }
        return minimumIndex;
    }

    public double calculateDistanceBetweenPointsWithHypot(
            double x1,
            double y1,
            double x2,
            double y2) {

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }


    @Override
    public void removeAllAvailableFeaturesOnMap() {
        mapView.getOverlays().clear();
        mapView.invalidate();
        allMarkers.clear();
        drawTarhZojoFard();
        drawTarhAsli();
    }

    /**
     * get zoom scale base on radius
     *
     * @param radius
     * @return
     */
    @Override
    public int calculateZoomScale(Float radius) {
        int zoomScale;
        switch (radius.intValue()) {
            case 100:
                zoomScale = 19;

                break;
            case 200:
            case 300:

                zoomScale = 18;
                break;

            case 400:
            case 500:
            case 600:
            case 700:

                zoomScale = 17;

                break;

            default:
                zoomScale = 16;
                break;


        }

        return zoomScale;
    }
    /**
     * Use the animation number in a
     * new camera position and then direct the
     * map camera to move to the new position
     */
    @Override
    public void rotateCamera(double bearing) {
        mapView.setMapOrientation(((float) bearing));
    }
    /**
     * set vertical bearing for camera
     */
    @Override
    protected void tiltCamera(double tilt) {

    }

    private void setTileSourceName(String tileSourceName){
        this.tileSourceName=tileSourceName;
    }


    public int getTileMinZoom() {
        return tileMinZoom;
    }

    public void setTileMinZoom(int tileMinZoom) {
        this.tileMinZoom = tileMinZoom;
    }

    public void setTileMaxZoom(int tileMaxZoom) {
        this.tileMaxZoom = tileMaxZoom;
    }
}


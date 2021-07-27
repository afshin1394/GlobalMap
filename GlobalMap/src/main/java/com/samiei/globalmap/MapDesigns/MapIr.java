package com.samiei.globalmap.MapDesigns;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.turf.TurfMeta;
import com.mapbox.turf.TurfTransformation;
import com.samiei.globalmap.Interfaces.IMapClickEvents;
import com.samiei.globalmap.Interfaces.IMapCondition;
import com.samiei.globalmap.MapInstructor;
import com.samiei.globalmap.Models.CirclePolygonModel;
import com.samiei.globalmap.Models.MapLineModel;
import com.samiei.globalmap.Models.MapObjectModel;
import com.samiei.globalmap.Models.PolygonModel;


import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ir.map.sdk_map.maps.MapView;
import retrofit2.Call;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOutlineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.turf.TurfConstants.UNIT_KILOMETERS;

/**
 * BASE CLASS MAP.IR extended from MAP_INSTRUCTOR
 */
public class MapIr extends MapInstructor {


    private static final String TAG = "MapIr";
    private Context context;
    private MapView mapView;
    private MapboxMap map;
    private Style mapStyle;
    private Style.Builder style;
    private SymbolManager symbolManager;


    HashMap<String, ArrayList<? extends MapObjectModel>> allLayers = new HashMap<>();

    /**
     * instantiating our map for the first time
     *
     * @param context
     * @param mapView
     * @param style
     * @param IMapCondition
     */
    public MapIr(Context context, MapView mapView, Style.Builder style, boolean showTarh, IMapCondition IMapCondition) {
        super(context);
        this.context = context;
        this.mapView = mapView;
        this.style = style;


        mapView.getMapAsync(mapboxMap -> {
            map = mapboxMap;
            mapboxMap.setStyle(style, style1 -> {

                mapStyle = style1;
                map.setMinZoomPreference(8d);
                symbolManager = new SymbolManager(mapView, map, mapStyle);
                symbolManager.setIconAllowOverlap(true);
                symbolManager.setTextAllowOverlap(true);
                if (showTarh) {
                    drawTarhAsli();
                    drawTarhZojoFard();
                }

                IMapCondition.readyToUse();


            });
        });

    }

    /**
     * get available map after creating an instance of it
     * in our view
     *
     * @param IMapCondition
     */
    public void getMap(IMapCondition IMapCondition) {

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                map = mapboxMap;
                mapboxMap.getStyle(new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapStyle = style;
                        if (symbolManager != null)
                            symbolManager = new SymbolManager(mapView, map, mapStyle);
                        IMapCondition.readyToUse();
                    }
                });
            }
        });

    }

    /**
     * showCurrent Location
     *
     * @param mapObjectModel
     */
    @Override
    public void showCurrentLocation(MapObjectModel mapObjectModel) {


        //image
        mapStyle.addImage(mapObjectModel.getImage_id(), mapObjectModel.getDrawable());


        //source
        Feature CurrentFeature = Feature.fromGeometry(Point.fromLngLat(mapObjectModel.getLatLng().longitude, mapObjectModel.getLatLng().latitude));
        FeatureCollection featureCollection = FeatureCollection.fromFeature(CurrentFeature);
        GeoJsonSource geoJsonSource = new GeoJsonSource(mapObjectModel.getSource_id(), featureCollection);
        mapStyle.addSource(geoJsonSource);

        //layer
        SymbolLayer symbolLayer = new SymbolLayer(mapObjectModel.getLayer_id(), mapObjectModel.getSource_id());
        symbolLayer.withProperties(PropertyFactory.iconIgnorePlacement(true),
                iconAllowOverlap(true),
                iconImage(mapObjectModel.getImage_id()),
                iconSize(mapObjectModel.getObjectSize()));

        mapStyle.addLayer(symbolLayer);

        ArrayList<MapObjectModel> mapObjectModels = new ArrayList<>();
        mapObjectModels.add(mapObjectModel);

        allLayers.put(mapObjectModel.getGroup_key(), mapObjectModels);


    }

    /**
     * draw circle polygon
     * with Turf interface
     *
     * @param circlePolygonModel
     */
    @Override
    public void drawCirclePolygon(CirclePolygonModel circlePolygonModel) {
        //set geoJsonSource
        Feature circlePolygon = Feature.fromGeometry(Point.fromLngLat(circlePolygonModel.getCenter().longitude, circlePolygonModel.getCenter().latitude));
        FeatureCollection featureCollection = FeatureCollection.fromFeature(circlePolygon);
        GeoJsonSource geoJsonSource = new GeoJsonSource(circlePolygonModel.getSource_id(), featureCollection);
        mapStyle.addSource(geoJsonSource);


        // Create and style a FillLayer based on information that will come from the Turf calculation
        FillLayer fillLayer = new FillLayer(circlePolygonModel.getLayer_id(),
                circlePolygonModel.getSource_id());
        fillLayer.setProperties(
                fillColor(circlePolygonModel.getFillColor()),
                fillOutlineColor(circlePolygonModel.getStrokeColor()),
                fillOpacity(circlePolygonModel.getFillOpacity()));

        if (allLayers.containsKey(CURRENT_LOCATION_GROUP_ID()))
            mapStyle.addLayerBelow(fillLayer, CURRENT_LOCATION_GROUP_ID());
        else
            mapStyle.addLayer(fillLayer);

        // Use Turf to calculate the Polygon's coordinates
        Polygon polygonArea = getTurfPolygon(Point.fromLngLat(circlePolygonModel.getCenter().longitude, circlePolygonModel.getCenter().latitude), circlePolygonModel.getRadius(), 720, UNIT_KILOMETERS);


        // Use Turf to calculate the Polygon's coordinates
        geoJsonSource.setGeoJson(Polygon.fromOuterInner(LineString.fromLngLats(TurfMeta.coordAll(polygonArea, false))));

        // Add to global layers
        ArrayList<MapObjectModel> mapCircleObjectModels = new ArrayList<>();
        mapCircleObjectModels.add(circlePolygonModel);
        allLayers.put(circlePolygonModel.getGroup_key(), mapCircleObjectModels);
    }

    private Polygon getTurfPolygon(@NonNull Point centerPoint, @NonNull double radius,
                                   @NonNull int steps, @NonNull String units)
    {
        return TurfTransformation.circle(centerPoint, radius, steps, units);
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

        if (allLayers.get(groupKey) != null) {
            ArrayList<? extends MapObjectModel> mapObjectModels = allLayers.get(groupKey);


            if (mapObjectModels != null)
            {
                for (MapObjectModel mapObjectModel : mapObjectModels)
                {
                    Log.i("removeExistingFeatures", "removeExistingFeatures: " + mapStyle.getLayers().contains(mapObjectModel.getLayer_id()));
                    mapStyle.removeLayer(mapObjectModel.getLayer_id());
                    mapStyle.removeSource(mapObjectModel.getSource_id());
                    Log.i("removeExistingFeatures", "removeExistingFeatures: " + mapStyle.getLayers().contains(mapObjectModel.getLayer_id()));
                }
                allLayers.remove(groupKey);
                return true;

            } else {
                return false;
            }

        } else {
            return false;

        }
    }

    /**
     * generate circle polygon with out
     * Turf interface
     *
     * @param centerCoordinates
     * @param radiusInKilometers
     * @param numberOfSides
     * @param fillColor
     * @param strokeColor
     * @return
     */
    private PolygonOptions generatePerimeter(LatLng centerCoordinates, double radiusInKilometers, int numberOfSides, int fillColor, int strokeColor) {
        List<com.mapbox.mapboxsdk.geometry.LatLng> positions = new ArrayList<>();
        double distanceX = radiusInKilometers / (111.319 * Math.cos(centerCoordinates.latitude * Math.PI / 180));
        double distanceY = radiusInKilometers / 110.574;

        double slice = (2 * Math.PI) / numberOfSides;

        double theta;
        double x;
        double y;
        com.mapbox.mapboxsdk.geometry.LatLng position;
        for (int i = 0; i < numberOfSides; ++i) {
            theta = i * slice;
            x = distanceX * Math.cos(theta);
            y = distanceY * Math.sin(theta);

            position = new com.mapbox.mapboxsdk.geometry.LatLng(centerCoordinates.latitude + y,
                    centerCoordinates.longitude + x);
            positions.add(position);
        }

        return new PolygonOptions()
                .addAll(positions)
                .fillColor(fillColor)
                .strokeColor(strokeColor)
                .alpha(0.4f);

    }

    /**
     * draw polyline
     *
     * @param mapLineModel
     */
    @Override
    public void drawLinePolygon(MapLineModel mapLineModel) {


// Create the LineString from the list of coordinates
        List<Point> routingCoordinates = initCoordinates(mapLineModel.getGeoPoints());


// Add source to map
        mapStyle.addSource(new GeoJsonSource(mapLineModel.getSource_id(),
                FeatureCollection.fromFeatures(new Feature[]{Feature.fromGeometry(
                        LineString.fromLngLats(routingCoordinates)
                )})));

// Add layer to map
        mapStyle.addLayer(new LineLayer(mapLineModel.getLayer_id(), mapLineModel.getSource_id()).withProperties(
                PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                PropertyFactory.lineWidth(5f),
                PropertyFactory.lineColor(mapLineModel.getLineColor())
        ));
        mapView.invalidate();


        ArrayList<MapLineModel> mapLineModels = new ArrayList<>();
        mapLineModels.add(mapLineModel);
        allLayers.put(mapLineModel.getGroup_key(), mapLineModels);
    }

    /**
     * init coordinates
     * of the optimized route
     *
     * @param geoPoints
     * @return List<Point>
     */
    private List<Point> initCoordinates(ArrayList<GeoPoint> geoPoints) {
        List<Point> routeCoordinates = new ArrayList<>();
        for (GeoPoint geoPoint : geoPoints) {
            routeCoordinates.add(Point.fromLngLat(geoPoint.getLongitude(), geoPoint.getLatitude()));
        }
        return routeCoordinates;
    }


    /**
     * show group of destinations
     *
     * @param mapObjectModels
     * @param group
     */
    @Override
    public void addGroupLocationLayer(ArrayList<MapObjectModel> mapObjectModels, String group) {

        for (MapObjectModel mapObjectModel : mapObjectModels) {

            //addImage
            mapStyle.addImage(mapObjectModel.getImage_id(), mapObjectModel.getDrawable());

            //set Manager
            mapObjectModel.setManager(symbolManager);
            //add Source
            Feature feature = Feature.fromGeometry(Point.fromLngLat(mapObjectModel.getLatLng().longitude, mapObjectModel.getLatLng().latitude));
            FeatureCollection featureCollection = FeatureCollection.fromFeature(feature);
            GeoJsonSource geoJsonSource = new GeoJsonSource(mapObjectModel.getSource_id(), featureCollection);
            mapStyle.addSource(geoJsonSource);


            //add layer
            SymbolLayer destinationSymbolLayer = new SymbolLayer(mapObjectModel.getLayer_id(), mapObjectModel.getSource_id());
            destinationSymbolLayer.setProperties(
                    PropertyFactory.iconAllowOverlap(true),
                    PropertyFactory.iconImage(mapObjectModel.getImage_id()),
                    PropertyFactory.iconSize(mapObjectModel.getObjectSize()),
                    PropertyFactory.iconOpacity(.8f));
            mapStyle.addLayer(destinationSymbolLayer);


        }

        allLayers.put(group, mapObjectModels);
    }


    /**
     * show single destination
     *
     * @param mapObjectModel
     */
    @Override
    public void addSingleLocationLayer(MapObjectModel mapObjectModel) {


        mapStyle.addImage(mapObjectModel.getImage_id(), mapObjectModel.getDrawable());

        //set Manager
        mapObjectModel.setManager(symbolManager);
        //add Source
        Feature feature = Feature.fromGeometry(Point.fromLngLat(mapObjectModel.getLatLng().longitude, mapObjectModel.getLatLng().latitude));
        FeatureCollection featureCollection = FeatureCollection.fromFeature(feature);
        GeoJsonSource geoJsonSource = new GeoJsonSource(mapObjectModel.getSource_id(), featureCollection);
        mapStyle.addSource(geoJsonSource);

        //add layer
        SymbolLayer destinationSymbolLayer = new SymbolLayer(mapObjectModel.getLayer_id(), mapObjectModel.getSource_id());
        destinationSymbolLayer.setProperties(
                PropertyFactory.iconAllowOverlap(true),
                PropertyFactory.iconImage(mapObjectModel.getImage_id()),
                PropertyFactory.iconSize(mapObjectModel.getObjectSize()),
                PropertyFactory.iconOpacity(.8f));
        mapStyle.addLayer(destinationSymbolLayer);


        ArrayList<MapObjectModel> destinationSymbolLayers = new ArrayList<>();
        destinationSymbolLayers.add(mapObjectModel);
        allLayers.put(mapObjectModel.getGroup_key(), destinationSymbolLayers);
    }





    /**
     * draw multi sided polygon
     *
     * @param polygonModels
     */
    @Override
    public void drawSellPolygon(ArrayList<PolygonModel> polygonModels)
    {
        ArrayList<MapObjectModel> polygons = new ArrayList<>();
        for (PolygonModel polygonModel : polygonModels) {
            List<List<Point>> polygonLists = new ArrayList<>();
            List<Point> polygonPoints = new ArrayList<>();

            for (LatLng latLng : polygonModel.getLatLngList()) {
                Point point = Point.fromLngLat(latLng.longitude, latLng.latitude);
                polygonPoints.add(point);
            }

            polygonLists.add(polygonPoints);


            Polygon polygon = Polygon.fromLngLats(polygonLists);
            FeatureCollection featureCollection = FeatureCollection.fromFeature(Feature.fromGeometry(polygon));
            GeoJsonSource geoJsonSource = new GeoJsonSource(polygonModel.getSource_id(), featureCollection);
            mapStyle.addSource(geoJsonSource);


//customize layer
            FillLayer fillLayer = new FillLayer(polygonModel.getLayer_id(), polygonModel.getSource_id());
            fillLayer.setProperties(
                    fillColor(polygonModel.getFillColor()),
                    fillOutlineColor(polygonModel.getStrokeColor()),
                    fillOpacity(polygonModel.getFillOpacity())
            );
            Log.i("layerId", "drawSellPolygon: " + polygonModel.getLayer_id());
            if (polygonModel.hasBorders()) {
                mapStyle.addSource(new GeoJsonSource(polygonModel.getSource_id() + "outlineShape",
                        FeatureCollection.fromFeatures(new Feature[]{Feature.fromGeometry(
                                LineString.fromLngLats(polygonPoints)
                        )})));
                Log.i("getStrokeColors", "drawSellPolygon: " + polygonModel.getStrokeColor());
// Add layer to map
                mapStyle.addLayer(new LineLayer(polygonModel.getLayer_id() + "outlineShape", polygonModel.getSource_id() + "outlineShape").withProperties(
                        PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                        PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                        PropertyFactory.lineWidth(5f),
                        PropertyFactory.lineColor(polygonModel.getStrokeColor())
                ));

            }
            mapView.invalidate();
/** Add layer to map **/

            mapStyle.addLayer(fillLayer);
            polygons.add(polygonModel);
        }

        allLayers.put(polygons.get(0).getGroup_key(), polygons);


    }

    /**
     * draw starting location
     *
     * @param mapObjectModel
     */
    @Override
    public void setStartLocation(MapObjectModel mapObjectModel) {

        mapStyle.addImage(mapObjectModel.getImage_id(), mapObjectModel.getDrawable());
        SymbolManager startSymbolManager = new SymbolManager(mapView, map, mapStyle);

        mapObjectModel.setManager(startSymbolManager);
/** Adding a GeoJson source for the SymbolLayer icons.**/
        ArrayList<Feature> startLocationFeatures = new ArrayList<>();
        Feature sampleFeature = Feature.fromGeometry(Point.fromLngLat(mapObjectModel.getLatLng().longitude, mapObjectModel.getLatLng().latitude));
        startLocationFeatures.add(sampleFeature);
        FeatureCollection featureCollection = FeatureCollection.fromFeatures(startLocationFeatures);
        GeoJsonSource geoJsonSource = new GeoJsonSource(mapObjectModel.getSource_id(), featureCollection);
        mapStyle.addSource(geoJsonSource);


        SymbolLayer symbolLayer = new SymbolLayer(mapObjectModel.getLayer_id(), mapObjectModel.getSource_id());
        symbolLayer.withProperties(PropertyFactory.iconAllowOverlap(true),
                PropertyFactory.iconImage(mapObjectModel.getImage_id()),
                iconIgnorePlacement(true),
                PropertyFactory.iconSize(mapObjectModel.getObjectSize()));


        mapStyle.addLayer(symbolLayer);
        ArrayList<MapObjectModel> mapObjectModels = new ArrayList<>();
        mapObjectModels.add(mapObjectModel);
        allLayers.put(mapObjectModel.getGroup_key(), mapObjectModels);

    }

    @Override
    public LatLng convertGeoPointToLatLng(GeoPoint geoPoint) {
        double lat = geoPoint.getLatitude();
        double lng = geoPoint.getLongitude();
        LatLng latLng = new LatLng(lat, lng);
        return latLng;

    }


    @Override
    public void moveCameraToSpecificPosition(LatLng latLng) {

        com.mapbox.mapboxsdk.geometry.LatLng latLngMapBox = new com.mapbox.mapboxsdk.geometry.LatLng(latLng.latitude, latLng.longitude);
        map.easeCamera(CameraUpdateFactory.newLatLng(latLngMapBox));

    }

    @Override
    public void zoomCameraToSpecificPosition(GeoPoint geoPoint, int zoomScale) {
        LatLng latLng = convertGeoPointToLatLng(geoPoint);
        com.mapbox.mapboxsdk.geometry.LatLng latLngMapBox = new com.mapbox.mapboxsdk.geometry.LatLng(latLng.latitude, latLng.longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngMapBox, zoomScale));
    }


    /**
     * on Map symbols and icons click listener//
     *
     * @param groupSymbols    layer_id
     * @param iMapClickEvents listener
     *                        <p>
     *                        USE THIS FOR SYMBOLS
     *                        AND MAP
     */
    @Override
    public void onMapContentClickListener(String groupSymbols, IMapClickEvents iMapClickEvents) {

        ArrayList<? extends MapObjectModel> mapObjectModels = getSymbolsById(groupSymbols);
        String[] layerIds = new String[mapObjectModels.size()];
        for (int i = 0; i < mapObjectModels.size(); i++) {
            layerIds[i] = mapObjectModels.get(i).getLayer_id();
        }
        Log.i("onMapClickListener", "onMapClickListener: " + layerIds.toString());

        if (mapObjectModels != null) {

            map.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                @Override
                public boolean onMapClick(@NonNull com.mapbox.mapboxsdk.geometry.LatLng point) {
                    PointF screenPoint = map.getProjection().toScreenLocation(point);
                    List<Feature> features = map.queryRenderedFeatures(screenPoint, layerIds);

                    if (!features.isEmpty()) {
                        Feature feature = features.get(0);
                        com.mapbox.mapboxsdk.geometry.LatLng latLng = convertToLatLng(feature);
                        Log.i("onMapClick", "onMapClick: " + latLng.getLatitude() + " " + latLng.getLongitude());
                        int index = findLayer(latLng, groupSymbols);
                        Log.i("onMapClick", "onMapClick: " + index);
                        if (index != -1)
                            iMapClickEvents.onMarkSingleTap(index, mapObjectModels.get(index));
                    } else {
                        Log.i("latLngTOUCH", "onMapClick: lat" + point.getLatitude() + "lon:" + point.getLongitude());
                        iMapClickEvents.onOtherItemsClick();
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void removeAllAvailableFeaturesOnMap()
    {
            if (allLayers != null) {
                for (String groupKey : allLayers.keySet()) {
                    ArrayList<? extends MapObjectModel> mapObjectModels = allLayers.get(groupKey);
                    for (MapObjectModel mapObjectModel : mapObjectModels) {
                        Log.i("removeExistingFeatures", "removeExistingFeatures: " + mapStyle.getLayers().contains(mapObjectModel.getLayer_id()));
                        mapStyle.removeLayer(mapObjectModel.getLayer_id());
                        mapStyle.removeSource(mapObjectModel.getSource_id());
                        Log.i("removeExistingFeatures", "removeExistingFeatures: " + mapStyle.getLayers().contains(mapObjectModel.getLayer_id()));
                    }
                    allLayers.remove(groupKey);
                }
            }

            drawTarhAsli();
            drawTarhZojoFard();
    }


    private int findLayer(com.mapbox.mapboxsdk.geometry.LatLng latLng, String groupSymbols) {
        ArrayList<? extends MapObjectModel> mapObjectModels = getSymbolsById(groupSymbols);
        for (int i = 0; i < mapObjectModels.size(); i++) {
            if (mapObjectModels.get(i).getLatLng().latitude - TOUCH_ACCURACY() <= latLng.getLatitude()
                    && mapObjectModels.get(i).getLatLng().latitude + TOUCH_ACCURACY() >= latLng.getLatitude()
                    && mapObjectModels.get(i).getLatLng().longitude - TOUCH_ACCURACY() <= latLng.getLongitude()
                    && mapObjectModels.get(i).getLatLng().longitude + TOUCH_ACCURACY() >= latLng.getLongitude()) {
                return i;
            }
        }

        return -1;
    }

    private com.mapbox.mapboxsdk.geometry.LatLng convertToLatLng(Feature feature) {
        Point symbolPoint = (Point) feature.geometry();
        return new com.mapbox.mapboxsdk.geometry.LatLng(symbolPoint.latitude(), symbolPoint.longitude());
    }


    public ArrayList<? extends MapObjectModel> getSymbolsById(String groupMapObjects) {
        return allLayers.get(groupMapObjects);
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
                zoomScale = 17;
                break;
            case 200:
            case 300:
                zoomScale = 16;
                break;

            case 400:
            case 500:
            case 600:
            case 700:
                zoomScale = 15;
                break;

            default:
                zoomScale = 14;
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
        Log.i("animateCamera", "animateCamera: " + bearing);

        map.easeCamera(CameraUpdateFactory
                .newCameraPosition(new CameraPosition.Builder()
                        .zoom(19)
                        .bearing(bearing)
                        .build()));


    }
    /**
     * set vertical bearing for camera
     */

    @Override
    public void tiltCamera(double tilt) {
        Log.i("tilttCamera", "tiltCamera: "+tilt);
        map.easeCamera(CameraUpdateFactory
                .newCameraPosition(new CameraPosition.Builder()
                        .zoom(19)
                        .tilt(tilt)
                        .build()));
    }





    /**
     * convert drawable to bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public float rotateToNextCheckPoint(com.mapbox.mapboxsdk.geometry.LatLng nextPoint, com.mapbox.mapboxsdk.geometry.LatLng currentPoint) {


        double lat1 = Math.toRadians(currentPoint.getLatitude());
        double lon1 = Math.toRadians(currentPoint.getLongitude());
        double lat2 = Math.toRadians(nextPoint.getLatitude());
        double lon2 = Math.toRadians(nextPoint.getLongitude());

        double cos1 = Math.cos(lat1);
        double cos2 = Math.cos(lat2);
        double sin1 = Math.sin(lat1);
        double sin2 = Math.sin(lat2);

        double delta = lon2 - lon1;
        double deltaCos = Math.cos(delta);
        double deltaSin = Math.sin(delta);

        double x = (cos1 * sin2) - (sin1 * cos2 * deltaCos);
        double y = deltaSin * cos2;
        double z = Math.toDegrees(Math.atan((-y / x)));
        if (x < 0) {
            z += 180;
        }
        double z2 = (z + 180) % 360 - 180;
        z2 = -Math.toRadians(z2);

        double angleRad = z2 - (Math.PI * 2 * Math.floor(z2 / (2 * Math.PI)));
        double angle = Math.toDegrees(angleRad);


        return ((float) -angle);
    }

    private String getEndpoint(Call call) {
        String endpoint = "";
        try {
            endpoint = call.request().url().toString();
            endpoint = endpoint.substring(endpoint.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return endpoint;
    }


}




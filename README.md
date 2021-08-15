# GlobalMap


This package is a module for using both mapbox and osmdroid features and services in an integrated and regular manner.
it is so easy to use and  has the capability for adding more map services to it.

## start ##

to use this package before everything you need to add your mapkey for mapbox if you want to use mapbox features:

```
public class BaseApplication extends Applicaion {

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        MapBox.getInstance(this, Constants.MAP_KEY);
    }

}
```


you can use integrated models for creating different elements in the map and it's a piece of cake to use  in both map designs and services
for example:


***Marker***

```
//setting a marker in map
  MapObjectModel currentLocation = new MapObjectModel.Builder()
                .setGroup_key(osmDroid.CURRENT_LOCATION_GROUP_ID())
                .setLayer_id(osmDroid.CURRENT_LOCATION_LAYER_ID())
                .setSource_id(osmDroid.CURRENT_LOCATION_SOURCE_ID())
                .setImage_id(osmDroid.CURRENT_LOCATION_IMAGE_ID())
                .setLatLng(new LatLng(osmDroid.getCurrentLocation().getLatitude(),osmDroid.getCurrentLocation().getLongitude()))
                .setObjectSize(1f)
                .setDrawable(getResources().getDrawable("your drawable"))
                .create();

```

***polygon***
```

    //setting a polygon in map
    PolygonModel polygonModel = new PolygonModel.Builder()
                        .setGroup_key(osmDroid.SELL_POLYGON_GROUP_ID())
                        .setSource_id(osmDroid.INCREMENTAL_LOCATION_SOURCE_ID())
                        .setLayer_id(osmDroid.INCREMENTAL_LOCATION_LAYER_ID())
                        .setLatLng(latLngs)
                        .setHasInfoWindow(false)
                        .setFillColor(Color.parseColor("your color"))
                        .setStrokeColor(Color.parseColor("your color"))
                        .setFillOpacity(3.0f)
                        .create();



```





***Circle Polygon***

```

//setting a circlePolygon in map
 CirclePolygonModel circlePolygonModel = new CirclePolygonModel.Builder()
                .setGroup_key(osmDroid.CIRCLE_POLYGON_GROUP_ID())
                .setLayer_id(osmDroid.CIRCLE_POLYGON_LAYER_ID())
                .setSource_id(osmDroid.CIRCLE_POLYGON_SOURCE_ID())
                .setFillColor(0x12121212)
                .setStrokeColor(Color.RED)
                .setStrokeWidth(1f)
                .setTitle("")
                .setFillOpacity(1f)
                .setRadius(finalRadius)
                .setCenter( new LatLng(osmDroid.getCurrentLocation().getLatitude(),osmDroid.getCurrentLocation().getLongitude()))
                .create();
```


after building these objects you just need to call the the constructor of each map design where ever you want to use these map designs
for example in an activity:

```
public class YourActivity extends Activity{

   MapBox mapbox;
   MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
     //mapView mapbox mapView you have set in your xml file
     mapbox = findViewById(R.id.mapBox);
     //mapType : mapbox  
     mapbox = new mapbox(YourActivity.this,mapView);
     
    }
    
    
    }
```
After creating your preffered object you can  start using it's features:


```
public class YourActivity extends Activity{

   MapBox mapbox;
   MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
     //mapView mapbox mapView you have set in your xml file
     mapbox = findViewById(R.id.mapBox);
     //mapType : mapbox  
     mapbox = new mapbox(YourActivity.this,mapView);
     mapbox.addSingleLocationLayer(mapObjectModel);
     mapbox.drawCirclePolygon(circlePolygonModel);
     mapbox.drawCirclePolygon(polygonModel);
    /** ...**/
    }
    
    
    }
```

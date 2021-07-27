package com.samiei.globalmap.Service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationProvider extends Service implements LocationListener
{
    private Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;
    double latitude = 0.0;
    double longitude = 0.0;
    double altitude = 0.0;
    float accurancy = 0.0f;
    float bearing = 0.0f;
    float speed = 0.0f;

    LocationManager locationManager;

    public LocationProvider(Context context)
    {
        this.context = context;
        latitude = 0.0;
        longitude = 0.0;
        getLocation();
    }


    public Location getLocation()
    {
        try
        {
            locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isGPSEnabled || isNetworkEnabled)
            {
                this.canGetLocation = true;
                if (isGPSEnabled)
                {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions((Activity)context , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 3, this);
                    if (locationManager != null)
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null)
                        {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            altitude = location.getAltitude();
                            accurancy = location.getAccuracy();
                            bearing = location.getBearing();
                            speed = location.getSpeed();
                        }
                    }
                }

                if (isNetworkEnabled)
                {
                    if (location == null)
                    {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions((Activity)context , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        }
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 3, this);
                        if (locationManager != null)
                        {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null){
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                altitude = location.getAltitude();
                                accurancy = location.getAccuracy();
                                bearing = location.getBearing();
                                speed = location.getSpeed();
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return location;
    }


    public double getLatitude()
    {
        //Log.d("location" , "lat : " + latitude + " provider : " + location.getProvider());
        if (location != null)
        {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude()
    {
        //Log.d("location" , "lng : " + longitude + " provider : " + location.getProvider());
        if (location != null)
        {
            longitude = location.getLongitude();
        }

        return longitude;
    }


    public double getAltitude()
    {
        if (location != null)
        {
            altitude = location.getAltitude();
        }
        return altitude;
    }


    public float getAccuracy()
    {
        if (location != null)
        {
            accurancy = location.getAccuracy();
        }
        return accurancy;
    }


    public float getBearing()
    {
        if (location != null)
        {
            bearing = location.getBearing();
        }
        return bearing;
    }

    public float getSpeed()
    {
        if (location != null)
        {
            speed = location.getSpeed()*3.6f;
        }
        return speed;
    }

    public boolean getHasAccess()
    {
        boolean hasAccess = false;
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                hasAccess = true;
            }
            else
            {
                hasAccess = false;
            }
        }
        else
        {
            hasAccess = true;
        }
        return hasAccess;
    }

    public boolean canGetLocation(){
        return this.canGetLocation;
    }
    @Override
    public void onLocationChanged(Location location) {
        if (location != null)
        {
            this.location = location;
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider)
    {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                LocationProvider.this.location = location;
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };




    public void stopLocationProvider() {
        if (locationManager !=null) locationManager.removeUpdates(locationListener);
        if (locationManager !=null) locationManager =null;
        if (locationListener !=null) locationListener =null;
    }

}

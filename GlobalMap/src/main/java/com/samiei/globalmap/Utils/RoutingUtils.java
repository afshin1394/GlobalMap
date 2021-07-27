package com.samiei.globalmap.Utils;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.samiei.globalmap.Enums.MapType;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class RoutingUtils
{
    public static final String drawableIdentifier = "drawable/ic_route_type_";

    public static ArrayList<GeoPoint> decode(String encodedString, boolean hasAltitude, MapType mapService) {
        double precision = 1.0 / 1E6;
        int index = 0;
        int len = encodedString.length();
        int lat = 0, lng = 0, alt = 0;
        ArrayList<GeoPoint> polyline = new ArrayList<GeoPoint>(len / 3);
        //capacity estimate: polyline size is roughly 1/3 of string length for a 5digits encoding, 1/5 for 10digits.

        while (index < len) {
            int b, shift, result;
            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            if (hasAltitude) {
                shift = result = 0;
                do {
                    b = encodedString.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dalt = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                alt += dalt;
            }
            GeoPoint p = null;
            switch (mapService)
            {

                case MapIR:
                    p = new GeoPoint(lat * precision * 10, lng * precision * 10, alt / 100);

                    break;
                case OsmDroid:
                    p = new GeoPoint(lat * precision, lng * precision, alt / 100);

                    break;
            }
            polyline.add(p);
        }

        //Log.d("BONUSPACK", "decode:string="+len+" points="+polyline.size());

        return polyline;
    }


    public static ArrayList<LatLng> decodeToLatLng(String encodedString, boolean hasAltitude,MapType mapService) {
        double precision = 1.0 / 1E6;
        int index = 0;
        int len = encodedString.length();
        int lat = 0, lng = 0, alt = 0;
        ArrayList<LatLng> polyline = new ArrayList<>(len / 3);
        //capacity estimate: polyline size is roughly 1/3 of string length for a 5digits encoding, 1/5 for 10digits.

        while (index < len) {
            int b, shift, result;
            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            if (hasAltitude) {
                shift = result = 0;
                do {
                    b = encodedString.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dalt = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                alt += dalt;
            }
            Log.i("decodeToLatLng", "decodeToLatLng: "+lat*precision);
            LatLng latLng = null;
            switch (mapService)
            {

                case MapIR:
                    latLng = new LatLng(lat * precision * 10, lng * precision * 10);

                    break;
                case OsmDroid:
                    latLng = new LatLng(lat * precision, lng * precision);

                    break;
            }
            polyline.add(latLng);
        }

        //Log.d("BONUSPACK", "decode:string="+len+" points="+polyline.size());

        return polyline;
    }



    /*public static int getImageResourceId(int type)
    {
        switch (type)
        {
            case 1:
                return R.drawable.next;
            case 2:
                return R.drawable.previous;
            default:
                return 0;
        }
    }*/



    public static ArrayList<LatLng> decodeToLatLngMapIr(String encodedString, boolean hasAltitude) {
        double precision = 1.0 / 1E6;
        int index = 0;
        int len = encodedString.length();
        int lat = 0, lng = 0, alt = 0;
        ArrayList<LatLng> polyline = new ArrayList<>(len / 3);
        //capacity estimate: polyline size is roughly 1/3 of string length for a 5digits encoding, 1/5 for 10digits.

        while (index < len) {
            int b, shift, result;
            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            if (hasAltitude) {
                shift = result = 0;
                do {
                    b = encodedString.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dalt = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                alt += dalt;
            }
            Log.i("decodeToLatLng", "decodeToLatLng: "+lat*precision);
            LatLng p = new LatLng(lat * precision*10, lng * precision*10);
            polyline.add(p);
        }

        //Log.d("BONUSPACK", "decode:string="+len+" points="+polyline.size());

        return polyline;
    }

    public static int identifyModifierForDrawable(String modifier) {

        if (modifier.contains("uturn"))
            return 15;
        if (modifier.contains("depart"))
            return 6;
        if (modifier.contains("arrive"))
            return 5;
        if (modifier.contains("slight right"))
            return 9;
        if (modifier.contains("right"))
            return 10;
        if (modifier.contains("sharp right"))
            return 10;
        if (modifier.contains("slight left"))
            return 16;
        if (modifier.contains("left"))
            return 15;
        if (modifier.contains("sharp left"))
            return 15;
        if (modifier.contains("straight"))
            return 4;

        return 0;
    }



    public static String identifyModifierForInstruction(String modifier) {

        if (modifier.contains("uturn"))
            return "دور برگردان";
        if (modifier.contains("depart"))
            return "شروع مسیر";
        if (modifier.contains("arrive"))
            return "به مقصد رسیدید";
        if (modifier.contains("slight right"))
            return "متمایل به راست حرکت کنید";
        if (modifier.contains("sharp right"))
            return "به سمت راست بپیچید";
        if (modifier.contains("right"))
            return "به سمت راست بپیچید";
        if (modifier.contains("slight left"))
            return "متمایل به چپ حرکت کنید";
        if (modifier.contains("sharp left"))
            return "به سمت چپ بپیچید";
        if (modifier.contains("left"))
            return "به سمت چپ بپیچید";
        if (modifier.contains("straight"))
            return "مستقیم به مسیرتان ادامه دهید";



        return "به مسیرتان ادامه دهید";
    }

}

package com.samiei.globalmap.Enums;

public enum MapType
{
    NONE, MapIR, OsmDroid , Offline;


    public static MapType getMapType(int mapType)
    {
        switch (mapType)
        {
            case 1:
                return MapType.OsmDroid;
            case 2:
                return MapType.MapIR;
            case 3:
                return MapType.Offline;
        }
        return MapType.NONE;
    }
}

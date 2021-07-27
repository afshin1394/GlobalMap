package com.samiei.globalmap.MapServices;//package com.saphamrah.PubFunc.Map.MapServices;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.graphhopper.GHRequest;
//import com.graphhopper.GHResponse;
//import com.graphhopper.GraphHopper;
//import com.graphhopper.PathWrapper;
//import com.graphhopper.routing.util.EncodingManager;
//
//public class OfflineService {
//
//
//    Context context;
//    GraphHopper graphHopper;
////Environment.getExternalStorageDirectory() + "/SapHamrah/Download/"
//    public OfflineService(Context context) {
//        this.context = context;
//        graphHopper = new GraphHopper().forServer();
//        graphHopper.setDataReaderFile("/WEB-INF/map-resource/hk_area.osm");
//        graphHopper.setGraphHopperLocation("/WEB-INF/graphhopper");
//        graphHopper.setEncodingManager(new EncodingManager("car"));
//
//        graphHopper.importOrLoad().forMobile();
//        Log.i("injection", "OfflineService: ");
//    }
//
//    public PathWrapper calculateOfflineRoute(String routeId,double[] origin,double[] destination){
//
//
//        GHRequest request = new GHRequest(origin[0], origin[1], destination[0], destination[1]);
//        request.setWeighting("fastest");
//        request.setVehicle("car");
//        GHResponse route = graphHopper.route(request);
//        PathWrapper pathWrapper =route.getAll().get(0);
//        Log.i("pathWrapper", "calculateOfflineRoute: "+pathWrapper);
//
//
//        return pathWrapper;
//
//    }
//
//}

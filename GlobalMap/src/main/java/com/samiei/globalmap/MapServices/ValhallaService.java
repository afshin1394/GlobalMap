package com.samiei.globalmap.MapServices;

import android.content.Context;


import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.samiei.globalmap.Api.APIServiceValhalla;
import com.samiei.globalmap.ApiClient.ApiClientGlobal;
import com.samiei.globalmap.Constants;
import com.samiei.globalmap.Enums.MapType;
import com.samiei.globalmap.Models.Direction.MapDirectionModel;
import com.samiei.globalmap.Models.Direction.MapMoveModel;
import com.samiei.globalmap.Responses.Valhalla.Location;
import com.samiei.globalmap.Responses.Valhalla.Maneuver;
import com.samiei.globalmap.Responses.Valhalla.OptimizedRouteResult;
import com.samiei.globalmap.Responses.Valhalla.SourceToTargetSuccessResult;
import com.samiei.globalmap.Responses.Valhalla.SourcesToTargetsFailedResult;
import com.samiei.globalmap.Responses.Valhalla.Trip;
import com.samiei.globalmap.ResultInterface.IResponse;
import com.samiei.globalmap.Utils.RoutingUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ValhallaService {

    Context context;
    String baseUrl;

    public ValhallaService(Context context,String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public void getOptimizedRoute(String jsonObjectAllData, IResponse retrofitResponse) {




        if (baseUrl.length() > 0) {
            APIServiceValhalla apiServiceValhalla = ApiClientGlobal.getInstance().getClientServiceValhalla(baseUrl);
            Call<Object> call = apiServiceValhalla.getOptimizedRoute(jsonObjectAllData);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    try {
                        Gson gson = new Gson();
                        if (response.isSuccessful()) {
                            String jsonObjectResponse = gson.toJson(response.body());
                            OptimizedRouteResult result = gson.fromJson(jsonObjectResponse, OptimizedRouteResult.class);
                            if (result != null && result.getTrip() != null) {
                                Trip trip = result.getTrip();
                                if (trip.getStatus() == 0 && trip.getLegs() != null && trip.getLegs().size() > 0 && trip.getSummary() != null) {
                                    ArrayList<MapMoveModel> mapMoveModels = new ArrayList<>();
                                    for (Maneuver maneuver : trip.getLegs().get(0).getManeuvers())
                                    {
                                        String identifier = "drawable/ic_route_type_" + maneuver.getType();
                                        String instruction = maneuver.getInstruction();
                                        if (maneuver.getVerbalPostTransitionInstruction() != null) {
                                            instruction += "\n" + maneuver.getVerbalPostTransitionInstruction();
                                        }

                                        List<LatLng> hashRoadLatLngs = RoutingUtils.decodeToLatLng(result.getTrip().getLegs().get(0).getShape(),false, MapType.OsmDroid);
                                        List<LatLng> hashRoadEachSection = hashRoadLatLngs.subList(maneuver.getBeginShapeIndex() , maneuver.getEndShapeIndex()+1);


                                        MapMoveModel mapMoveModel = new MapMoveModel(maneuver.getLength(),maneuver.getTime(),identifier, instruction ,hashRoadEachSection);
                                        mapMoveModels.add(mapMoveModel);
                                    }

                                    LatLng startingLocation = new LatLng(result.getTrip().getLocations().get(0).getLat() , result.getTrip().getLocations().get(0).getLon());
                                    LatLng endingLocation = new LatLng(result.getTrip().getLocations().get(1).getLat() , result.getTrip().getLocations().get(1).getLon());
                                    double overAllDuration = trip.getSummary().getTime();
                                    double overAllDistance = trip.getSummary().getLength();
                                    MapDirectionModel mapDirectionModel = new MapDirectionModel.Builder()
                                            .setRouteId(result.getId())
                                            .setHashRoad(result.getTrip().getLegs().get(0).getShape())
                                            .setStartingPoint(startingLocation)
                                            .setEndingPoint(endingLocation)
                                            .setOverAllDistance(overAllDistance)
                                            .setOverAllDuration(overAllDuration)
                                            .setMovements(mapMoveModels)
                                            .create();






                                    String mapDirectionJson=gson.toJson(mapDirectionModel);
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(mapDirectionJson);
                                    retrofitResponse.onSuccess(arrayList);
                                } else {
                                    retrofitResponse.onFailed("Exception","inComplete response routing");
                                }

                            }

                        } else {
                            SourcesToTargetsFailedResult responseError = gson.fromJson(gson.toJson(response), SourcesToTargetsFailedResult.class);
                            String endpoint = getEndpoint(call);
                            String message = String.format("message : %1$s \n code : %2$s * %3$s", responseError.getError(), responseError.getErrorCode(), endpoint);
                            retrofitResponse.onFailed(Constants.HTTP_ERROR, message);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        retrofitResponse.onFailed(Constants.HTTP_ERROR,"error get Data Routing");

                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    String endpoint = getEndpoint(call);
                    String errorBody =  String.format("%1$s * %2$s", t.getMessage(), endpoint);
                    retrofitResponse.onFailed(Constants.HTTP_ERROR, errorBody);

                }
            });
        } else {


            retrofitResponse.onFailed(Constants.HTTP_SERVER_UNAVAILABLE, "server not found");

//
        }
    }

    public void getMatrixDistance(JSONObject jsonObjectAllData,String requestId,String ACTIVITY_NAME, IResponse retrofitResponse) {
        try {
        if (baseUrl.length() > 0) {
           APIServiceValhalla apiServiceValhalla = ApiClientGlobal.getInstance().getClientServiceValhalla(baseUrl);

            Call<Object> call = apiServiceValhalla.getSourcesToTargets(jsonObjectAllData.toString(), requestId);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    try {
                        Gson gson = new Gson();

                        if (response.isSuccessful()) {
                            SourceToTargetSuccessResult result = gson.fromJson(gson.toJson(response.body()), SourceToTargetSuccessResult.class);
                            if (result != null) {
                                if (result.getSources() != null && result.getSources().size() > 0 && result.getSourcesToTargets() != null && result.getSourcesToTargets().size() > 0) {
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(result.getSourcesToTargets().get(0));
                                    retrofitResponse.onSuccess(arrayList);
                                } else {
                                    retrofitResponse.onFailed(Constants.HTTP_ERROR, "error get sources or sources-to-targets");
                                }
                            } else {
                                String endpoint = getEndpoint(call);
                                String.format("%1$s * %2$s", "result is null", endpoint);
                                retrofitResponse.onFailed(Constants.HTTP_NULL_RESPONSE, "result is null");
                            }
                        } else {
                            SourcesToTargetsFailedResult responseError = gson.fromJson(gson.toJson(response), SourcesToTargetsFailedResult.class);
                            String endpoint = getEndpoint(call);
                            String message = String.format("message : %1$s \n code : %2$s * %3$s", responseError.getError(), responseError.getErrorCode(), endpoint);

                            retrofitResponse.onFailed(Constants.HTTP_ERROR, message);

                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();

                        retrofitResponse.onFailed(Constants.HTTP_ERROR, "error getting data from server");

                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    String endpoint = getEndpoint(call);
                    String.format("%1$s * %2$s", t.getMessage(), endpoint);

                    retrofitResponse.onFailed(Constants.HTTP_ERROR, "error getData");

                }
            });
        } else {

            retrofitResponse.onFailed(Constants.HTTP_ERROR, "cant find server");
        }

    } catch( Exception e)
    {
        e.printStackTrace();

        retrofitResponse.onFailed(Constants.HTTP_ERROR,"error occurred");

    }

}



    private JsonObject locationToJson(Location location)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("lat", location.getLat());
        jsonObject.addProperty("lon", location.getLon());
        return jsonObject;
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

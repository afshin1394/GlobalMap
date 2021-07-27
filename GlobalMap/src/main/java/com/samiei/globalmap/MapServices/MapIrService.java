package com.samiei.globalmap.MapServices;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.samiei.globalmap.Api.APIServiceMapIr;
import com.samiei.globalmap.ApiClient.ApiClientGlobal;
import com.samiei.globalmap.Constants;
import com.samiei.globalmap.Enums.MapType;
import com.samiei.globalmap.Enums.NavigationType;
import com.samiei.globalmap.Models.Direction.MapDirectionModel;
import com.samiei.globalmap.Models.Direction.MapMoveModel;
import com.samiei.globalmap.Models.Matrix.MatrixParam;
import com.samiei.globalmap.Responses.MapIr.Matrix.MatrixSuccessResponse;
import com.samiei.globalmap.Responses.MapIr.OptimizedResponse.OptimizedRouteResult;
import com.samiei.globalmap.Responses.MapIr.OptimizedResponse.Step;
import com.samiei.globalmap.ResultInterface.IResponse;
import com.samiei.globalmap.Utils.RoutingUtils;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Service
public class MapIrService {


    Context context;
    String mapKey;
    public MapIrService(Context context,String mapKey) {
        this.context = context;
        this.mapKey = mapKey;
        Log.i("injection", "MapIrService: ");
    }

    private Call checkNavigationType(NavigationType navigationType, String latLngsString, boolean showAlternatives , boolean showSteps){
        APIServiceMapIr apiServiceMapIr = ApiClientGlobal.getInstance().getClientServiceMapIr(mapKey);

        Call<OptimizedRouteResult> call;
        switch (navigationType) {
            case DRIVING:
                call = apiServiceMapIr.getDrivingRoute(latLngsString, showAlternatives, showSteps);
                break;

            case ON_FOOT:
                call = apiServiceMapIr.getFootRoute(latLngsString, showAlternatives, showSteps);
                break;

            case BICYCLE:
                call = apiServiceMapIr.getBicycleRoute(latLngsString, showAlternatives, showSteps);
                break;

            case TARH_ASLI:
                call = apiServiceMapIr.getDrivingTarhRoute(latLngsString, showAlternatives, showSteps);
                break;

            case ZOJO_FARD:
                call = apiServiceMapIr.getDrivingZojoFardRoute(latLngsString, showAlternatives, showSteps);
                break;
            default:
                call = null;
        }
        return call;
    }
    public void getNavigationRoute(String routeId,NavigationType navigationType, LatLng[] latLngs, boolean showAlternatives, boolean showSteps, IResponse retrofitResponse) {

        String latLngString = convertArrayToApiString(latLngs);

        if (latLngString.length()>0)
        {
            Call<OptimizedRouteResult> call = checkNavigationType(navigationType, latLngString, showAlternatives, showSteps);
            if (call != null)
            {
                call.enqueue(new Callback<OptimizedRouteResult>() {
                    @Override
                    public void onResponse(Call<OptimizedRouteResult> call, Response<OptimizedRouteResult> response) {
                        Log.i("responseCode", "onResponse: " + response.body().getCode());
                        Log.i("responseBody", "onResponse: " + response.body());
                        Log.i("fetchRout", "onResponse");
                        if (response.raw().body() != null)
                        {
                            long contentLength = response.raw().body().contentLength();
                        }
                        try {
                            if (response.isSuccessful()) {
                                OptimizedRouteResult result = response.body();
                                if (result != null) {
                                    Log.i("fetchRout", "result not null"+result.getCode());
                                    ArrayList<MapMoveModel> mapMoveModels = new ArrayList<>();
                                    for (Step step : result.getRoutes()[0].getLegs()[0].getSteps()) {
                                        //guidance
                                        int modifier = RoutingUtils.identifyModifierForDrawable(step.getManeuver().getModifier() + step.getManeuver().getType());
                                        String guidanceSigns ="drawable/ic_route_type_" + modifier;

                                        //instruction
                                        String instruction = step.getName();
                                        String instructionExtension = RoutingUtils.identifyModifierForInstruction(step.getManeuver().getModifier() + step.getManeuver().getType());

                                        if (step.getDistance() != null)
                                        {
                                            instruction += "\n" + instructionExtension ;
                                        }

                                        if (step.getDistance()!= 0.0d)
                                        {
                                            instruction += "\n"+ "distance:"+" "+ step.getDistance() + "meter";
                                        }
                                        List<LatLng> hashRoadSection = RoutingUtils.decodeToLatLng(step.getGeometry(),false, MapType.MapIR);
                                        MapMoveModel mapMoveModel = new MapMoveModel(step.getDistance() , step.getDuration() , guidanceSigns , instruction , hashRoadSection);
                                        mapMoveModels.add(mapMoveModel);
                                    }

                                    MapDirectionModel mapDirectionModel = new MapDirectionModel.Builder()
                                            .setRouteId(routeId)
                                            .setHashRoad(result.getRoutes()[0].getGeometry())
                                            .setStartingPoint(new LatLng( result.getWayPoints()[0].getLocation()[1] , result.getWayPoints()[0].getLocation()[0]))
                                            .setEndingPoint(new LatLng( result.getWayPoints()[1].getLocation()[1] , result.getWayPoints()[1].getLocation()[0]))
                                            .setOverAllDistance(result.getRoutes()[0].getDistance())
                                            .setOverAllDuration(result.getRoutes()[0].getDuration())
                                            .setMovements(mapMoveModels)
                                            .create();
                                    Gson gson = new Gson();
                                    String mapDirectionJson=gson.toJson(mapDirectionModel);
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(mapDirectionJson);
                                    retrofitResponse.onSuccess(arrayList);

                                } else {
                                    Log.i("fetchRoute", "result not null " + result.getMessage());

                                    String endpoint = getEndpoint(call);
                                    retrofitResponse.onFailed(Constants.HTTP_NULL_RESPONSE, "result is null");
                                }
                            } else {
                                String endpoint = getEndpoint(call);
                                String message = String.format("error body : %1$s , code : %2$s * %3$s", response.message(), response.code(), endpoint);
                                retrofitResponse.onFailed(Constants.HTTP_ERROR, message);
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            retrofitResponse.onFailed(Constants.HTTP_EXCEPTION, exception.toString());
                        }

                    }


                    @Override
                    public void onFailure(Call<OptimizedRouteResult> call, Throwable t) {
                        Log.i("fetchRout", "onFailure: ");
                        String endpoint = getEndpoint(call);
                        retrofitResponse.onFailed("HTTP_EXCEPTION", t.getMessage());
                    }
                });
            } else {
                Log.i("fetchRout", "onFailure: ");
                retrofitResponse.onFailed(Constants.HTTP_EXCEPTION, "invalidMap");
            }
        }
        else{
            Log.i("fetchRout", "onFailure: ");

            retrofitResponse.onFailed(Constants.HTTP_EXCEPTION,"error getData routing");
        }
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

    public void getMatrixDistance(MatrixParam[] startingPoints, MatrixParam[] destinationPoints, boolean sort, String ACTIVITY_NAME, IResponse iResponse)
    {
        String startingString = convertToApiMatrixFormat(startingPoints);
        String destinationString = convertToApiMatrixFormat(destinationPoints);

        APIServiceMapIr apiServiceMapIr = ApiClientGlobal.getInstance().getClientServiceMapIr(mapKey);

        Call<MatrixSuccessResponse> call = apiServiceMapIr.getMatrixDistance(startingString,destinationString,sort);

        String endpoint = getEndpoint(call);
        call.enqueue(new Callback<MatrixSuccessResponse>() {
            @Override
            public void onResponse(Call<MatrixSuccessResponse> call, Response<MatrixSuccessResponse> response) {
                Log.i("responseBody", "onResponse: " + response.body());

                Log.i("fetchRoute", "onResponse");
                if (response.raw().body() != null)
                {
                    long contentLength = response.raw().body().contentLength();
                }
                try {
                    if (response.isSuccessful()) {
                        MatrixSuccessResponse result = response.body();

                        if (result != null) {

                            ArrayList arrayList = new ArrayList();
                            arrayList.add(result);
                            iResponse.onSuccess(arrayList);

                        } else {
                            Log.i("fetchMatrix", "result not null " + result);

                            iResponse.onFailed(Constants.HTTP_NULL_RESPONSE, "result is null");
                        }
                    } else {

                        String message = String.format("error body : %1$s , code : %2$s * %3$s", response.message(), response.code(), endpoint);
                        iResponse.onFailed(Constants.HTTP_ERROR, message);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    iResponse.onFailed(Constants.HTTP_EXCEPTION, exception.toString());
                }

            }


            @Override
            public void onFailure(Call<MatrixSuccessResponse> call, Throwable t) {
                Log.i("fetchMatrix", "onFailure: ");
                iResponse.onFailed(Constants.HTTP_EXCEPTION, t.getMessage());
            }
        });

    }
    private String convertToApiMatrixFormat(MatrixParam[] matrixParams)
    {
        String matrixParamString = "";
        for (int i = 0; i < matrixParams.length; i++)
        {
            if (i < matrixParams.length - 1)
                matrixParamString += matrixParams[i].getId() + "," + matrixParams[i].getLatLngs().latitude + "," + matrixParams[i].getLatLngs().longitude + "|";
            else
                matrixParamString += matrixParams[i].getId() + "," + matrixParams[i].getLatLngs().latitude + "," + matrixParams[i].getLatLngs().longitude ;

        }
        return matrixParamString;

    }
    private String convertArrayToApiString(LatLng[] latLngs) {

        String latLngString = "";

        if (latLngs.length > 0) {
            for (int i = 0; i < latLngs.length; i++) {
                if (i != latLngs.length - 1)
                    latLngString +=  latLngs[i].longitude +"%2C"+ latLngs[i].latitude+"%3B" ;
                else
                    latLngString += latLngs[i].longitude  +"%2C" + latLngs[i].latitude;
            }
        }
        return latLngString;
    }

}

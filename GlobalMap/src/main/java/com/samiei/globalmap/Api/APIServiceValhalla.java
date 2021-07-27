package com.samiei.globalmap.Api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceValhalla
{


    @GET("sources_to_targets")
    Call<Object> getSourcesToTargets(@Query("json") String jsonSourcesAndDestinations, @Query("id") String id);


    @GET("optimized_route")
    Call<Object> getOptimizedRoute(@Query("json") String jsonDatas);

}

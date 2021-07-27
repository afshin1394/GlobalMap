package com.samiei.globalmap.Api;



import com.samiei.globalmap.Responses.MapIr.Matrix.MatrixSuccessResponse;
import com.samiei.globalmap.Responses.MapIr.OptimizedResponse.OptimizedRouteResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServiceMapIr {
    @GET("distancematrix/")
    Call<MatrixSuccessResponse> getMatrixDistance(@Query("origins") String origins ,
                                                  @Query("destinations") String destinations ,
                                                  @Query("sorted") boolean sorted);


    @GET("/routes/route/v1/driving/{coordinates}")
    Call<OptimizedRouteResult> getDrivingRoute(@Path("coordinates") String latLngs ,
                                               @Query("alternatives") boolean alternatives,
                                               @Query("steps") boolean steps);

    @GET("/routes/bicycle/v1/driving/{coordinates}")
    Call<OptimizedRouteResult> getBicycleRoute(@Path("coordinates") String latLngs ,
                                               @Query("alternatives") boolean alternatives,
                                               @Query("steps") boolean steps);

    @GET("/routes/foot/v1/driving/{coordinates}")
    Call<OptimizedRouteResult> getFootRoute(@Path("coordinates") String latLngs ,
                                            @Query("alternatives") boolean alternatives,
                                            @Query("steps") boolean steps);

    @GET("tarh/v1/driving/{coordinates}")
    Call<OptimizedRouteResult> getDrivingTarhRoute(@Path("coordinates") String latLngs ,
                                                   @Query("alternatives") boolean alternatives,
                                                   @Query("steps") boolean steps);

    @GET("zojofard/v1/driving/")
    Call<OptimizedRouteResult> getDrivingZojoFardRoute(@Path("coordinates") String latLngs ,
                                                       @Query("alternatives") boolean alternatives,
                                                       @Query("steps") boolean steps);




}

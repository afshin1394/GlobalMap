package com.samiei.globalmap.Responses.Valhalla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourcesToTargetsFailedResult
{

    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

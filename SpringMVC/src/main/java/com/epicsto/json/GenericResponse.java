package com.epicsto.json;

import java.util.ArrayList;

/**
 * Created on 9/5/17.
 */
public class GenericResponse {

    private ArrayList responseArray ;
    private String status;
    private String responseCode ;
    private String message ;

    public GenericResponse(String status, String responseCode, String message) {
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
    }

    public GenericResponse(ArrayList responseArray ,String status, String responseCode, String message) {
        this.responseArray = responseArray;
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(ArrayList responseArray) {
        this.responseArray = responseArray;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "responseArray=" + responseArray +
                ", status='" + status + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

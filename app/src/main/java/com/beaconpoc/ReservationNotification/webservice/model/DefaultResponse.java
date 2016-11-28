package com.beaconpoc.ReservationNotification.webservice.model;

/**
 * Created by 472641 on 11/10/2016.
 */
public class DefaultResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

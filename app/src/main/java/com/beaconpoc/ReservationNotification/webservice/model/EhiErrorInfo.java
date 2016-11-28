package com.beaconpoc.ReservationNotification.webservice.model;

/**
 * Created by 472641 on 11/10/2016.
 */
public class EhiErrorInfo {
    private String message;
    private String url;

    public EhiErrorInfo(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

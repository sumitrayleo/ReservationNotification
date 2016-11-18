package com.beaconpoc.ReservationNotification.webservice.model;

import java.util.List;

/**
 * Created by 472641 on 11/18/2016.
 */
public class PushNotificationFcmModel {
    private String memberId;
    private List<ReservationModel> reservations;
    private List<PointOfInterestModel> poi;

    public PushNotificationFcmModel( ) {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<ReservationModel> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationModel> reservations) {
        this.reservations = reservations;
    }

    public List<PointOfInterestModel> getPoi() {
        return poi;
    }

    public void setPoi(List<PointOfInterestModel> poi) {
        this.poi = poi;
    }
}

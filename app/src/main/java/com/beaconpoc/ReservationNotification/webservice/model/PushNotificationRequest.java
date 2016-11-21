package com.beaconpoc.ReservationNotification.webservice.model;

import android.support.annotation.NonNull;

/**
 * Created by 472641 on 11/10/2016.
 */
public class PushNotificationRequest {

    private String token;
    private String identifier;
    private double longitude;
    private double latitude;
    private String memberId;
    private String deviceId;

    public PushNotificationRequest() {

    }

    public PushNotificationRequest(Builder builder) {
        this.memberId = builder.memberId;
        this.deviceId = builder.deviceId;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.identifier = builder.identifier;
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public String getIdentifier() {
        return identifier;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public static final class Builder {
        private String memberId;
        private String deviceId;
        private double latitude;
        private double longitude;
        private String identifier;
        private String token;

        public Builder memberId(@NonNull String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder deviceId(@NonNull String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder identifier(@NonNull String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder token(@NonNull String token) {
            this.token = token;
            return this;
        }

        public PushNotificationRequest build() {
            return new PushNotificationRequest(this);
        }
    }
}

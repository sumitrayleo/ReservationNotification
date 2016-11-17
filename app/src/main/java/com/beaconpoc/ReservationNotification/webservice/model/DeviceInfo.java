package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 472641 on 11/10/2016.
 */
public class DeviceInfo implements Parcelable {
    private String locationDetails;
    private String message;
    private String assetId;
    private long longitude;
    private long latitude;
    private String uuid;
    private String region;

    public DeviceInfo() {

    }

    protected DeviceInfo(Parcel in) {
        locationDetails = in.readString();
        message = in.readString();
        assetId = in.readString();
        longitude = in.readLong();
        latitude = in.readLong();
        uuid = in.readString();
        region = in.readString();
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public static final Creator<DeviceInfo> CREATOR = new Creator<DeviceInfo>() {
        @Override
        public DeviceInfo createFromParcel(Parcel in) {
            return new DeviceInfo(in);
        }

        @Override
        public DeviceInfo[] newArray(int size) {
            return new DeviceInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationDetails);
        dest.writeString(message);
        dest.writeString(assetId);
        dest.writeLong(longitude);
        dest.writeLong(latitude);
        dest.writeString(uuid);
        dest.writeString(region);
    }
}

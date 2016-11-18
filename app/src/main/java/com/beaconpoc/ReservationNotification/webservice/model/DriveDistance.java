package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 472641 on 11/18/2016.
 */
public class DriveDistance implements Parcelable {
    private double latitude;
    private double longitude;
    private double driveDistance;
    private String landMarkInstructions;

    public DriveDistance() {

    }

    protected DriveDistance(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        driveDistance = in.readDouble();
        landMarkInstructions = in.readString();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(double driveDistance) {
        this.driveDistance = driveDistance;
    }

    public String getLandMarkInstructions() {
        return landMarkInstructions;
    }

    public void setLandMarkInstructions(String landMarkInstructions) {
        this.landMarkInstructions = landMarkInstructions;
    }

    public static final Creator<DriveDistance> CREATOR = new Creator<DriveDistance>() {
        @Override
        public DriveDistance createFromParcel(Parcel in) {
            return new DriveDistance(in);
        }

        @Override
        public DriveDistance[] newArray(int size) {
            return new DriveDistance[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeDouble(driveDistance);
        dest.writeString(landMarkInstructions);
    }
}

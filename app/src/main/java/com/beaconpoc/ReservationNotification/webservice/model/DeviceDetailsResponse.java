package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 472641 on 11/10/2016.
 */
public class DeviceDetailsResponse implements Parcelable {
    private boolean success;
    private String message;
    private DeviceInfo deviceInfo;

    public DeviceDetailsResponse() {

    }

    protected DeviceDetailsResponse(Parcel in) {
        success = in.readByte() != 0;
        message = in.readString();
        deviceInfo = in.readParcelable(DeviceInfo.class.getClassLoader());
    }

    public static final Creator<DeviceDetailsResponse> CREATOR = new Creator<DeviceDetailsResponse>() {
        @Override
        public DeviceDetailsResponse createFromParcel(Parcel in) {
            return new DeviceDetailsResponse(in);
        }

        @Override
        public DeviceDetailsResponse[] newArray(int size) {
            return new DeviceDetailsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
        dest.writeParcelable(deviceInfo, flags);
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

}

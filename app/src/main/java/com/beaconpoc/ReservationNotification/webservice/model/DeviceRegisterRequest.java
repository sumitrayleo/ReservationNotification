package com.beaconpoc.ReservationNotification.webservice.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 472641 on 11/10/2016.
 */
public class DeviceRegisterRequest {
    @SerializedName("pictures")
    private List<DeviceInfo> listOfDeviceInfo;
    private String appName;

    public DeviceRegisterRequest() {

    }

    public List<DeviceInfo> getListOfDeviceInfo() {
        return listOfDeviceInfo;
    }

    public void setListOfDeviceInfo(List<DeviceInfo> listOfDeviceInfo) {
        this.listOfDeviceInfo = listOfDeviceInfo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}

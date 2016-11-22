package com.beaconpoc.ReservationNotification.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 472641 on 11/21/2016.
 */
public class LocationUpdateUtils {

    private static final String TAG = "LocationUpdateUtils";
    private static final int MIN_UPDATE_INTERVAL = 10000;
    private static final int MIN_UPDATE_DIST = 1000;
    private static LocationUpdateUtils locationUpdateUtils;
    private final Context context;
    private LocationManager locationManager;
    private LocationListener privateLocationListener;
    private Location location;
    private OnLocationFetchedListener onLocationFetchedListener;

    private LocationUpdateUtils(Context context) {
        this.context = context;
        initLocationUpdatesSetup();
    }

    public static LocationUpdateUtils getInstance(Context context) {
        if (locationUpdateUtils == null) {
            locationUpdateUtils = new LocationUpdateUtils(context);
        }
        return locationUpdateUtils;
    }

    public Location getLocation() {
        return location;
    }

    public void setOnLocationFetchedListener(OnLocationFetchedListener onLocationFetchedListener){
        this.onLocationFetchedListener = onLocationFetchedListener;
    }

    public void removeListener(){
        this.onLocationFetchedListener = null;
    }

    public void initLocationUpdatesSetup() {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        privateLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "inside onLocationChanged");
                LocationUpdateUtils.this.location = location;
                if(onLocationFetchedListener != null){
                    onLocationFetchedListener.onLocationFetched();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, "inside onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d(TAG, "inside onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d(TAG, "inside onProviderDisabled");
            }
        };

        monitorProviderChanges();
    }

    @SuppressWarnings("MissingPermission")
    private void monitorProviderChanges() {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_UPDATE_INTERVAL, MIN_UPDATE_DIST, privateLocationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_UPDATE_INTERVAL, MIN_UPDATE_DIST, privateLocationListener);
    }

    public interface OnLocationFetchedListener{
        void onLocationFetched();
    }

}

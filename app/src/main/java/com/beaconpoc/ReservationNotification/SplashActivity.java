package com.beaconpoc.ReservationNotification;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.beaconpoc.ReservationNotification.utils.LocationUpdateUtils;
import com.beaconpoc.ReservationNotification.utils.PermissionUtils;
import com.beaconpoc.ReservationNotification.webservice.ResponseCallBackHandler;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceDetailsResponse;
import com.beaconpoc.ReservationNotification.webservice.model.EhiErrorInfo;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class SplashActivity extends Activity {

    private static final String TAG = "splash_activity";
    private static final int PERMISSION_REQUEST_CODE = 5;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Fetching Location");
        progressDialog.setCancelable(false);
        new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    launchHomeScreen();
                }
            }
        }.start();

//        checkPermissionForLocationUpdate();
    }

    @TargetApi(23)
    private void checkPermissionForLocationUpdate() {
        String[] permission = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};

        progressDialog.show();
        if (PermissionUtils.isLocationPermitted(this)) {
            LocationUpdateUtils locationUpdateUtils = LocationUpdateUtils.getInstance(this);
            locationUpdateUtils.setOnLocationFetchedListener(new LocationFetchedListener());
        } else {
            requestPermissions(permission, PERMISSION_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationUpdateUtils locationUpdateUtils = LocationUpdateUtils.getInstance(this);
                    locationUpdateUtils.setOnLocationFetchedListener(new LocationFetchedListener());
                } else {
                    return;
                }
            }
        }
    }

    private void launchHomeScreen() {
        startActivity(MainActivity.intentMainActivity(this));
        finish();
    }

    private class LocationFetchedListener implements LocationUpdateUtils.OnLocationFetchedListener {
        @Override
        public void onLocationFetched() {
            Log.d(TAG, "inside onLocationFetched");
            progressDialog.dismiss();
            launchHomeScreen();
            LocationUpdateUtils.getInstance(SplashActivity.this).removeListener();

        }
    }
}

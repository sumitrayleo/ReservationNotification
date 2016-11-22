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
        progressDialog.setCancelable(false);

        retrieveDeviceId("98765342", "9860", "5678");
        checkPermissionForLocationUpdate();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionForLocationUpdate() {
        String[] permission = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
        if (PermissionUtils.isLocationPermitted(this)) {
            LocationUpdateUtils.getInstance(this);
        } else {
            requestPermissions(permission, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSION_REQUEST_CODE) {
            return;
        }

        for (final int result : grantResults) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                LocationUpdateUtils.getInstance(this);
            }
        }
    }

    private void launchHomeScreen(@NonNull DeviceDetailsResponse response) {
        if (response != null) {
            startActivity(MainActivity.intentMainActivity(this, response));
        }
        finish();
    }

    private void retrieveDeviceId(@NonNull String uuid, @NonNull String region, @NonNull String assetId) {
        progressDialog.show();

        ResponseCallBackHandler<DeviceDetailsResponse> callBackHandler = new ResponseCallBackHandler<DeviceDetailsResponse>() {
            @Override
            public void success(DeviceDetailsResponse response) {
                Log.d(TAG, "inside success callback");
                progressDialog.dismiss();
                launchHomeScreen(response);
            }

            @Override
            public void failure(EhiErrorInfo errorInfo) {
                Log.d(TAG, "inside failure callback");
                progressDialog.dismiss();
                finish();
            }
        };

        ((MyApplication) getApplication()).getEhiNotificationServiceApi().
                getDeviceInfoById(uuid, region, assetId, callBackHandler);

    }
}

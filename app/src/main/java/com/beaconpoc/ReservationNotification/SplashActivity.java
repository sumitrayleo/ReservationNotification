package com.beaconpoc.ReservationNotification;

import android.*;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.beaconpoc.ReservationNotification.utils.LocationUpdateUtils;
import com.beaconpoc.ReservationNotification.utils.PermissionUtils;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private static final int PERMISSION_REQUEST_CODE = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkPermissionForLocationUpdate();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionForLocationUpdate() {
        String[] permission = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
        if (PermissionUtils.isLocationPermitted(this)) {
            LocationUpdateUtils.getInstance(this);
            launchHomeScreenDelayed();
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
                launchHomeScreen();
            }
        }
    }

    private void launchHomeScreen() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void launchHomeScreenDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

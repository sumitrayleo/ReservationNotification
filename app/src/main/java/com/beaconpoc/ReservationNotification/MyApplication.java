package com.beaconpoc.ReservationNotification;

import android.app.Application;
import android.util.Log;

import com.beaconpoc.ReservationNotification.estimote.BeaconID;
import com.beaconpoc.ReservationNotification.estimote.BeaconNotificationsManager;
import com.estimote.sdk.EstimoteSDK;
import com.google.firebase.iid.FirebaseInstanceId;


public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    public static String FCM_TOKEN = "";

    @Override
    public void onCreate() {
        super.onCreate();

        EstimoteSDK.initialize(getApplicationContext(), "reservation-notification-mm4", "920ab396769875a0521f9e5283c83158");
        FCM_TOKEN = FirebaseInstanceId.getInstance().getToken();
        // enable debug-level logging
//        EstimoteSDK.enableDebugLogging(true);
    }

    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        /**
         * TODO : Call WebService to get the uuidString
         */


        String uuidString = "b9407f30-f5f8-466e-aff9-25556b57fe6d:13531:47";
        beaconNotificationsManager.addNotification(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 13531, 47),
                "Hello Guest.",
                "Goodbye Guest.");
        beaconNotificationsManager.startMonitoringAllBeacons(uuidString);

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }
}

package com.beaconpoc.ReservationNotification.estimote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.beaconpoc.ReservationNotification.MainActivity;
import com.beaconpoc.ReservationNotification.MyApplication;
import com.beaconpoc.ReservationNotification.constant.ReservationNotificationConstants;
import com.beaconpoc.ReservationNotification.webservice.ResponseCallBackHandler;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceDetailsResponse;
import com.beaconpoc.ReservationNotification.webservice.model.EhiErrorInfo;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BeaconNotificationsManager {

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;

    private List<Region> regionsToMonitor = new ArrayList<>();
    private HashMap<String, String> enterMessages = new HashMap<>();
    private HashMap<String, String> exitMessages = new HashMap<>();


    private static HashMap<String, Long> enteredBeaconList = new HashMap<String, Long>();
    public static final long DO_NOT_NOTIFY_HOURS = 24;

    private Context context;
    private int notificationID = 0;

    public BeaconNotificationsManager(Context context) {
        this.context = context;
        beaconManager = new BeaconManager(context);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                if(!list.isEmpty()) {
                    Beacon beacon = list.get(0);
                    String beaconIdentification = beacon.getProximityUUID()+":"+beacon.getMajor()+":"+beacon.getMinor();
                    Log.d(TAG, "onEnteredRegion:" + beaconIdentification);
                    if (enteredBeaconList.get(beaconIdentification)==null ||
                            (enteredBeaconList.get(beaconIdentification)!=null && ((System.currentTimeMillis()- enteredBeaconList.get(beaconIdentification))/(1000*60*60)) > DO_NOT_NOTIFY_HOURS)){
                        enteredBeaconList.put(beaconIdentification, System.currentTimeMillis());
                        retrieveDeviceId(beacon.getProximityUUID().toString(), Integer.toString(beacon.getMajor()), Integer.toString(beacon.getMinor()));
                    }
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
            }
        });
    }

    public void addNotification(BeaconID beaconID, String enterMessage, String exitMessage) {
        Region region = beaconID.toBeaconRegion();
        enterMessages.put(region.getIdentifier(), enterMessage);
        exitMessages.put(region.getIdentifier(), exitMessage);
        regionsToMonitor.add(region);
    }

    public void startMonitoringAllBeacons(final String uuidString) {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(uuidString, null, null, null));
            }
        });
    }

    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (Region region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });
    }

    public void retrieveDeviceId(@NonNull String uuid, @NonNull String region, @NonNull String assetId) {

        ResponseCallBackHandler<DeviceDetailsResponse> callBackHandler = new ResponseCallBackHandler<DeviceDetailsResponse>() {
            @Override
            public void success(DeviceDetailsResponse response) {
                Log.d(TAG, "inside success callback");
                if (response != null && response.getDeviceInfo() != null) {
                    showNotification(response.getDeviceInfo().getMessage());
                }
            }

            @Override
            public void failure(EhiErrorInfo errorInfo) {
                Log.d(TAG, "inside failure callback");
                return;
            }
        };

        ((MyApplication) context.getApplicationContext()).getEhiNotificationServiceApi().
                getDeviceInfoById(uuid, region, assetId, callBackHandler);

    }

    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra(ReservationNotificationConstants.BEACON_PUSH_MESSAGE, message);
        resultIntent.putExtra(ReservationNotificationConstants.BEACON_FLOW, true);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Reservation Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }
}

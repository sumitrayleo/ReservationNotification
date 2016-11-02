package com.beaconpoc.ReservationNotification.estimote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.beaconpoc.ReservationNotification.MainActivity;
import com.beaconpoc.ReservationNotification.webservice.ServiceUtils;
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
    private static int notificationEnterCount = 0;
    private static int notificationExitCount = 0;

    private Context context;

    private int notificationID = 0;

    public BeaconNotificationsManager(Context context) {
        this.context = context;
        beaconManager = new BeaconManager(context);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                StringBuilder sb = new StringBuilder();
                int i =1;
                for (Beacon beacon: list) {
                    if (beacon!=null){
                        sb.append("Beacon-").append(i).append(" ");
                        sb.append(":").append(beacon.getMajor()).append(":").append(beacon.getMinor());//append(beacon.getProximityUUID())
                        i++;
                    }
                }
                Log.d(TAG, "onEnteredRegion: " + region.getIdentifier());

                //TODO : Call Webservice to retrieve Location and show location in Welcome message
                //ServiceUtils.retrieveDeviceInformation();

                String message = enterMessages.get(region.getIdentifier());
                if (message != null) {
                    if (notificationEnterCount < 1){
                        showNotification(message+">"+notificationEnterCount+"-"+sb.toString());
                    }
                    notificationEnterCount++;
                }

                // WebService Here to trigger backend check-in processes
                ServiceUtils.postPushNotificationData();
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
                String message = exitMessages.get(region.getIdentifier());
                if (message != null) {
                    if (notificationExitCount==notificationEnterCount-1){
                        showNotification(message+">"+notificationEnterCount+"-"+" "+region.getMajor()+":"+region.getMinor());
                    }
                    notificationExitCount++;
                }
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
                beaconManager.startMonitoring(new Region(uuidString,null,null,null));
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

    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Reservation Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }
}

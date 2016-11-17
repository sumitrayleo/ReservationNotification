package com.beaconpoc.ReservationNotification.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.beaconpoc.ReservationNotification.MainActivity;
import com.beaconpoc.ReservationNotification.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aparupa on 11/11/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMMsgService";

    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " + remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());

        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        sendNotification(remoteMessage);

    }

    private void sendNotification(RemoteMessage remoteMessage) {

        String memberId = remoteMessage.getData().get("memberId");
        try {
            JSONArray arr = new JSONArray(remoteMessage.getData().get("poi"));
            for(int i = 0; i < 1; i++) {

                JSONObject jsonObject = arr.getJSONObject(i);
                JSONObject promoOffersObj = jsonObject.getJSONObject("promoOffers");

                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                /*.setLargeIcon()*/
                        .setContentTitle(jsonObject.getString("name"))
                        .setContentText("LoyaltyCode - " + promoOffersObj.getString("loyaltyCode"))
                        .setAutoCancel(false)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

      }

    }


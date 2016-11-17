package com.beaconpoc.ReservationNotification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beaconpoc.ReservationNotification.constant.ReservationNotificationConstants;
import com.beaconpoc.ReservationNotification.webservice.ResponseCallBackHandler;
import com.beaconpoc.ReservationNotification.webservice.ServiceUtils;
import com.beaconpoc.ReservationNotification.webservice.model.DefaultResponse;
import com.beaconpoc.ReservationNotification.webservice.model.EhiErrorInfo;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationRequest;
import com.estimote.sdk.SystemRequirementsChecker;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    Button pushNotification;
    Button retrieveDeviceInformation;
    ProgressBar progressBar;
    private static String serviceIdentifier;
    private static String pushNotificationServiceIdentifier="pushNotificationService";
    private static String retrieveDeviceInformationServiceIdentifier="retrieveDeviceInformationService";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushNotification =(Button) findViewById(R.id.button);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        retrieveDeviceInformation =(Button) findViewById(R.id.button1);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        retrieveDeviceInformation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new ExecuteTask().execute(retrieveDeviceInformationServiceIdentifier);
            }
        });

        pushNotification.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                sendPushNotificationRequest();
                new ExecuteTask().execute(pushNotificationServiceIdentifier);
            }
        });

    }

    private void sendPushNotificationRequest() {
        ResponseCallBackHandler<DefaultResponse> callBackHandler = new ResponseCallBackHandler<DefaultResponse>() {
            @Override
            public void success(DefaultResponse response) {
                Log.d(TAG, "inside success callback");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(EhiErrorInfo errorInfo) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "inside failure callback" + errorInfo.getMessage());
            }
        };

        PushNotificationRequest request = new PushNotificationRequest.Builder()
                .deviceId("1234")
                .identifier("Airlines")
                .latitude(80)
                .longitude(90)
                .memberId("1")
                .token(ReservationNotificationConstants.FCM_TOKEN).build();

        ((MyApplication) getApplication()).getEhiNotificationServiceApi().submitPushNotificationRequest(request, callBackHandler);
    }


    class ExecuteTask extends AsyncTask<String, Integer, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Sending...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {

            String[] paramIdentifier=params;
            serviceIdentifier=paramIdentifier[0];

            if(pushNotificationServiceIdentifier.equalsIgnoreCase(serviceIdentifier)){
                return ServiceUtils.postPushNotificationData(ReservationNotificationConstants.FCM_TOKEN);
            }else if(retrieveDeviceInformationServiceIdentifier.equalsIgnoreCase(serviceIdentifier)){
                return ServiceUtils.retrieveDeviceInformation();
            }

            return serviceIdentifier;

        }

        @SuppressLint("ShowToast")
        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.GONE);
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            System.out.println("Webservice Response:::::"+result);

        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        MyApplication app = (MyApplication) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
        } else if (!app.isBeaconNotificationsEnabled()) {
            Log.d(TAG, "Enabling beacon notifications");
            app.enableBeaconNotifications();
        }
    }
}

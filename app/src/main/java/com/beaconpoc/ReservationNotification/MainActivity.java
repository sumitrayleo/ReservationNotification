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

import com.beaconpoc.ReservationNotification.webservice.ServiceUtils;
import com.estimote.sdk.SystemRequirementsChecker;
import com.google.firebase.iid.FirebaseInstanceId;

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
                new ExecuteTask().execute(pushNotificationServiceIdentifier);
            }
        });

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
                return ServiceUtils.postPushNotificationData(MyApplication.FCM_TOKEN);
            }else if(retrieveDeviceInformationServiceIdentifier.equalsIgnoreCase(serviceIdentifier)){
                return ServiceUtils.retrieveDeviceInformation();
            }

            return serviceIdentifier;

        }

        @SuppressLint("ShowToast")
        @Override
        protected void onPostExecute(String result) {
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

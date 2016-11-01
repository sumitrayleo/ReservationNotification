package com.beaconpoc.ReservationNotification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beaconpoc.ReservationNotification.webservice.ServiceUtils;
import com.estimote.sdk.SystemRequirementsChecker;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    Button pushNotification;
    Button retrieveDeviceInformation;
    ProgressBar progressBar;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushNotification =(Button) findViewById(R.id.button);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        pushNotification.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new ExecuteTask().execute();
            }
        });



        retrieveDeviceInformation =(Button) findViewById(R.id.button1);
        progressBar1=(ProgressBar) findViewById(R.id.progressBar1);
        progressBar1.setVisibility(View.GONE);


        retrieveDeviceInformation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                new ExecuteTask().execute();
            }
        });

    }


    class ExecuteTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {

           // return ServiceUtils.postPushNotificationData();
            return ServiceUtils.retrieveDeviceInformation();
        }

        @SuppressLint("ShowToast")
        @Override
        protected void onPostExecute(String result) {
            progressBar1.setVisibility(View.GONE);
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

package com.beaconpoc.ReservationNotification;

import android.app.Application;

import com.beaconpoc.ReservationNotification.constant.EhiNotificationConstants;
import com.beaconpoc.ReservationNotification.constant.ReservationNotificationConstants;
import com.beaconpoc.ReservationNotification.estimote.BeaconID;
import com.beaconpoc.ReservationNotification.estimote.BeaconNotificationsManager;
import com.beaconpoc.ReservationNotification.webservice.EhiNotificationInterceptor;
import com.beaconpoc.ReservationNotification.webservice.EhiNotificationRetrofitApi;
import com.beaconpoc.ReservationNotification.webservice.EhiNotificationServiceApi;
import com.beaconpoc.ReservationNotification.webservice.EhiNotificationServiceApiImpl;
import com.estimote.sdk.EstimoteSDK;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    private EhiNotificationServiceApi ehiNotificationServiceApi;

    @Override
    public void onCreate() {
        super.onCreate();

        EstimoteSDK.initialize(getApplicationContext(), "reservation-notification-mm4", "920ab396769875a0521f9e5283c83158");
        ReservationNotificationConstants.FCM_TOKEN = FirebaseInstanceId.getInstance().getToken();
        createRetrofitService();


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

    public EhiNotificationServiceApi getEhiNotificationServiceApi() {
        return ehiNotificationServiceApi;
    }

    private void createRetrofitService() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new EhiNotificationInterceptor());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }

        clientBuilder.connectTimeout(EhiNotificationConstants.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.readTimeout(EhiNotificationConstants.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(EhiNotificationConstants.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        Cache cache = new Cache(this.getCacheDir(), EhiNotificationConstants.HTTP_CACHE_SIZE_IN_BYTES);
        clientBuilder.cache(cache);

        Retrofit retrofitApi = new Retrofit.Builder()
                .baseUrl(EhiNotificationConstants.BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EhiNotificationRetrofitApi ehiNotificationRetrofitApi = retrofitApi.create(EhiNotificationRetrofitApi.class);

        ehiNotificationServiceApi = new EhiNotificationServiceApiImpl(ehiNotificationRetrofitApi);

    }


}

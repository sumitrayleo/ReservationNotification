package com.beaconpoc.ReservationNotification.webservice;

import android.os.Handler;
import android.os.Looper;

import com.beaconpoc.ReservationNotification.webservice.model.DefaultResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceDetailsResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceRegisterRequest;
import com.beaconpoc.ReservationNotification.webservice.model.EhiErrorInfo;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 472641 on 11/10/2016.
 */
public class EhiNotificationServiceApiImpl implements EhiNotificationServiceApi {

    private EhiNotificationRetrofitApi notificationRetrofitApi;

    public EhiNotificationServiceApiImpl(EhiNotificationRetrofitApi notificationRetrofitApi) {
        this.notificationRetrofitApi = notificationRetrofitApi;
    }

    @Override
    public void submitPushNotificationRequest(final PushNotificationRequest pushNotificationRequest, final ResponseCallBackHandler<DefaultResponse> callback) {
        Runnable retryCall = new Runnable() {
            @Override
            public void run() {
                submitPushNotificationRequest(pushNotificationRequest, callback);
            }
        };

        Call<DefaultResponse> call = notificationRetrofitApi.submitPushNotificationRequest(pushNotificationRequest);
        call.enqueue(createRetrofitCallbackWithErrorHandling(callback, retryCall));
    }

    @Override
    public void submitRegisterDeviceRequest(final DeviceRegisterRequest deviceRegisterRequest, final ResponseCallBackHandler<DefaultResponse> callback) {
        Runnable retryCall = new Runnable() {
            @Override
            public void run() {
                submitRegisterDeviceRequest(deviceRegisterRequest, callback);
            }
        };

        Call<DefaultResponse> call = notificationRetrofitApi.submitRegisterDeviceRequest(deviceRegisterRequest);
        call.enqueue(createRetrofitCallbackWithErrorHandling(callback, retryCall));
    }

    @Override
    public void getDeviceInfoById(final String uuid, final String region, final String assetId, final ResponseCallBackHandler<DeviceDetailsResponse> callback) {
        Runnable retryCall = new Runnable() {
            @Override
            public void run() {
                getDeviceInfoById(uuid, region, assetId, callback);
            }
        };

        Call<DeviceDetailsResponse> call = notificationRetrofitApi.getDeviceInfoById(uuid, region, assetId);
        call.enqueue(createRetrofitCallbackWithErrorHandling(callback, retryCall));

    }

    private <T> Callback<T> createRetrofitCallbackWithErrorHandling(final ResponseCallBackHandler<T> callback, final Runnable retryCall) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.success(response.body());
                } else {
                    buildErrorAndSendCallback(call, null);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                buildErrorAndSendCallback(call, t);
            }

            private void buildErrorAndSendCallback(final Call<T> call, final Throwable t) {
                EhiErrorInfo errorInfo = new EhiErrorInfo(t != null ? t.getMessage() : "No Throwable", call.request().url().toString());
                callback.failure(errorInfo);
                //dispatchRetry(retryCall);
            }
        };
    }

    private void dispatchRetry(final Runnable retryCall) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                retryCall.run();
            }
        });
    }
}

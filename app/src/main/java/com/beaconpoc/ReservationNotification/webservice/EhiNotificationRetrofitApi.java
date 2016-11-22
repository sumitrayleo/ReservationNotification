package com.beaconpoc.ReservationNotification.webservice;

import com.beaconpoc.ReservationNotification.webservice.model.DefaultResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceDetailsResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceRegisterRequest;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 472641 on 11/10/2016.
 */
public interface EhiNotificationRetrofitApi {

    @POST("api/booking/notify/info")
    Call<DefaultResponse> submitPushNotificationRequest(@Body PushNotificationRequest pushNotificationRequest);

    @POST("info/booking/device/registerDevice ")
    Call<DefaultResponse> submitRegisterDeviceRequest(@Body DeviceRegisterRequest deviceRegisterRequest);

    @GET("api/booking/device/info")
    Call<DeviceDetailsResponse> getDeviceInfoById(@Query("uuid") String uuid, @Query("region") String region, @Query("assetId") String assetId);

}

package com.beaconpoc.ReservationNotification.webservice;

import com.beaconpoc.ReservationNotification.webservice.model.DefaultResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceDetailsResponse;
import com.beaconpoc.ReservationNotification.webservice.model.DeviceRegisterRequest;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationRequest;

/**
 * Created by 472641 on 11/10/2016.
 */
public interface EhiNotificationServiceApi {

    void submitPushNotificationRequest(final PushNotificationRequest pushNotificationRequest, final ResponseCallBackHandler<DefaultResponse> callback);

    void submitRegisterDeviceRequest(final DeviceRegisterRequest deviceRegisterRequest, final ResponseCallBackHandler<DefaultResponse> callback);

    void getDeviceInfoById(final String uuid, final String region, final String assetId, final ResponseCallBackHandler<DeviceDetailsResponse> callback);
}

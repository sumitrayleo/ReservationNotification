package com.beaconpoc.ReservationNotification.webservice;

import com.beaconpoc.ReservationNotification.webservice.model.EhiErrorInfo;

/**
 * Created by 472641 on 11/10/2016.
 */
public interface ResponseCallBackHandler<T> {
    void success(T response);

    void failure(EhiErrorInfo errorInfo);
}

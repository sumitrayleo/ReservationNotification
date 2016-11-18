package com.beaconpoc.ReservationNotification.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beaconpoc.ReservationNotification.R;
import com.beaconpoc.ReservationNotification.utils.FormatUtils;

/**
 * Created by Aparupa on 11/17/2016.
 */

public class ReservationDetailsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflateView = inflater.inflate(R.layout.fragment_reservation_details, container, false);
        setData(inflateView);
        return inflateView;
    }

    private void setData(View inflateView){
        ((TextView)inflateView.findViewById(R.id.reservation_time_period))
                .setText(FormatUtils.getDate((long)1.4824044E9)+" ~ " + FormatUtils.getDate((long)1.4824908E9));

    }

}

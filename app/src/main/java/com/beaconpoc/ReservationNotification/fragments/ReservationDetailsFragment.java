package com.beaconpoc.ReservationNotification.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beaconpoc.ReservationNotification.R;

/**
 * Created by Aparupa on 11/17/2016.
 */

public class ReservationDetailsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reservation_details, container, false);
    }
}

package com.beaconpoc.ReservationNotification.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beaconpoc.ReservationNotification.R;
import com.beaconpoc.ReservationNotification.webservice.model.DriveDistance;

public class PromoOfferDetailsFragment extends BaseFragment {
    private static final String ARG_DRIVE_DISTANCE = "driveDistance";
    private DriveDistance driveDistance;

    public PromoOfferDetailsFragment() {
        // Required empty public constructor
    }

    public static PromoOfferDetailsFragment newInstance(DriveDistance driveDistance) {
        PromoOfferDetailsFragment fragment = new PromoOfferDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DRIVE_DISTANCE, driveDistance);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            driveDistance = getArguments().getParcelable(ARG_DRIVE_DISTANCE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_promo_offer_details, container, false);
    }

}

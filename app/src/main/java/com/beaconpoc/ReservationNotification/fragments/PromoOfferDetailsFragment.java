package com.beaconpoc.ReservationNotification.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View inflateView = inflater.inflate(R.layout.fragment_promo_offer_details, container, false);
        ((TextView) inflateView.findViewById(R.id.landMarkInstructions))
                .setText(driveDistance != null ? driveDistance.getLandMarkInstructions() : "");

        return inflateView;
    }

}

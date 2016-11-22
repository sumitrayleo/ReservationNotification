package com.beaconpoc.ReservationNotification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.beaconpoc.ReservationNotification.fragments.PromoOfferDetailsFragment;

public class PromoOfferDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_offer_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = PromoOfferDetailsFragment.newInstance(((MyApplication)getApplication()).getPushNotificationFcmModel().getReservations().get(0).getDriveDistance());
            fragmentManager.beginTransaction().add(R.id.container, fragment).commit();
        }
    }


}

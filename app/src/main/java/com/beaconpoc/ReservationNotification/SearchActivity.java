package com.beaconpoc.ReservationNotification;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beaconpoc.ReservationNotification.constant.AppBarType;

/**
 * Created by Aparupa on 24-11-2016.
 */

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupAppBar(getString(R.string.search_header), AppBarType.NONE);

    }
}

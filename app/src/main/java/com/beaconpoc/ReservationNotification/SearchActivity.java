package com.beaconpoc.ReservationNotification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.beaconpoc.ReservationNotification.constant.AppBarType;
import com.estimote.sdk.SystemRequirementsChecker;

/**
 * Created by Aparupa on 24-11-2016.
 */

public class SearchActivity extends BaseActivity {

    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupAppBar(getString(R.string.search_header), AppBarType.NONE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication app = (MyApplication) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
        } else if (!app.isBeaconNotificationsEnabled()) {
            Log.d(TAG, "Enabling beacon notifications");
            app.enableBeaconNotifications();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

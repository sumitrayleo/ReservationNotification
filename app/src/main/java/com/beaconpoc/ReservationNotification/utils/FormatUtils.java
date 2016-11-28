package com.beaconpoc.ReservationNotification.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class FormatUtils {

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MMM-yyyy", cal).toString();
        return date;
    }

}

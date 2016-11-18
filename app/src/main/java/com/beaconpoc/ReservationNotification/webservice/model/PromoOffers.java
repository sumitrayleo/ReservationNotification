package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 472641 on 11/18/2016.
 */
public class PromoOffers implements Parcelable {
    private List<RulesModel> rules;
    private String loyaltyCode;

    public PromoOffers() {

    }

    protected PromoOffers(Parcel in) {
        rules = in.createTypedArrayList(RulesModel.CREATOR);
        loyaltyCode = in.readString();
    }

    public List<RulesModel> getRules() {
        return rules;
    }

    public void setRules(List<RulesModel> rules) {
        this.rules = rules;
    }

    public String getLoyaltyCode() {
        return loyaltyCode;
    }

    public void setLoyaltyCode(String loyaltyCode) {
        this.loyaltyCode = loyaltyCode;
    }

    public static final Creator<PromoOffers> CREATOR = new Creator<PromoOffers>() {
        @Override
        public PromoOffers createFromParcel(Parcel in) {
            return new PromoOffers(in);
        }

        @Override
        public PromoOffers[] newArray(int size) {
            return new PromoOffers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(rules);
        dest.writeString(loyaltyCode);
    }
}

package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 472641 on 11/18/2016.
 */
public class PointOfInterestModel implements Parcelable {
    private String name;
    private PromoOffers promoOffers;
    private DriveDistance driveDistance;
    private String specialInstructions;

    public PointOfInterestModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PromoOffers getPromoOffers() {
        return promoOffers;
    }

    public void setPromoOffers(PromoOffers promoOffers) {
        this.promoOffers = promoOffers;
    }

    public DriveDistance getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(DriveDistance driveDistance) {
        this.driveDistance = driveDistance;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    protected PointOfInterestModel(Parcel in) {
        name = in.readString();
        promoOffers = in.readParcelable(PromoOffers.class.getClassLoader());
        driveDistance = in.readParcelable(DriveDistance.class.getClassLoader());
        specialInstructions = in.readString();
    }

    public static final Creator<PointOfInterestModel> CREATOR = new Creator<PointOfInterestModel>() {
        @Override
        public PointOfInterestModel createFromParcel(Parcel in) {
            return new PointOfInterestModel(in);
        }

        @Override
        public PointOfInterestModel[] newArray(int size) {
            return new PointOfInterestModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(promoOffers, flags);
        dest.writeParcelable(driveDistance, flags);
        dest.writeString(specialInstructions);
    }
}

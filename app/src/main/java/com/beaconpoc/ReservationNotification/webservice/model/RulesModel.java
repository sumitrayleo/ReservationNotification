package com.beaconpoc.ReservationNotification.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 472641 on 11/18/2016.
 */
public class RulesModel implements Parcelable {

    private String promoCodeId;
    private String description;
    private float discount;
    private String discountType;
    private String category;

    public RulesModel() {

    }

    protected RulesModel(Parcel in) {
        promoCodeId = in.readString();
        description = in.readString();
        discount = in.readFloat();
        discountType = in.readString();
        category = in.readString();
    }

    public String getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static final Creator<RulesModel> CREATOR = new Creator<RulesModel>() {
        @Override
        public RulesModel createFromParcel(Parcel in) {
            return new RulesModel(in);
        }

        @Override
        public RulesModel[] newArray(int size) {
            return new RulesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(promoCodeId);
        dest.writeString(description);
        dest.writeFloat(discount);
        dest.writeString(discountType);
        dest.writeString(category);
    }
}

package com.beaconpoc.ReservationNotification.model;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class PromoItem {

    private String title;
    private String description;
    private String promocode;
    private int icon;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getThumbnail() {
            return icon;
        }

        public void setThumbnail(int icon) {
            this.icon = icon;
        }


}

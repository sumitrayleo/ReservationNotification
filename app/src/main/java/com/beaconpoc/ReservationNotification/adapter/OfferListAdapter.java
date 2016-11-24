package com.beaconpoc.ReservationNotification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beaconpoc.ReservationNotification.R;
import com.beaconpoc.ReservationNotification.model.OfferItem;

import java.util.List;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class OfferListAdapter  extends RecyclerView.Adapter<OfferListAdapter.CustomViewHolder> {
    private List<OfferItem> promoItemList;
    private Context mContext;

    public OfferListAdapter(Context context, List<OfferItem> promoItemList) {
        this.promoItemList = promoItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        OfferItem promoItem = promoItemList.get(i);

        customViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        customViewHolder.title.setText(promoItem.getTitle());
        customViewHolder.description.setText(promoItem.getDescription());
        customViewHolder.promocode.setText(promoItem.getPromocode());
    }

    @Override
    public int getItemCount() {
        return (null != promoItemList ? promoItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView title;
        protected TextView description;
        protected TextView promocode;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.offer_icon);
            this.title = (TextView) view.findViewById(R.id.offer_title);
            this.description = (TextView) view.findViewById(R.id.offer_desc);
            this.promocode = (TextView) view.findViewById(R.id.offer_promo_code);
        }
    }
}
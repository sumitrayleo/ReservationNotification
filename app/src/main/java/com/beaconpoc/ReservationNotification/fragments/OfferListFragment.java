package com.beaconpoc.ReservationNotification.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.beaconpoc.ReservationNotification.OfferListAdapter;
import com.beaconpoc.ReservationNotification.R;
import com.beaconpoc.ReservationNotification.model.PromoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class OfferListFragment extends BaseFragment {

    private List<PromoItem> promoList;
    private RecyclerView mRecyclerView;
    private OfferListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflateView = inflater.inflate(R.layout.fragment_offerlist, container, false);
        mRecyclerView = (RecyclerView) inflateView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        promoList = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            PromoItem item = new PromoItem();
            item.setTitle("ECar with 20% Discount");
            item.setDescription("Application Discount");
            item.setPromocode("Promo Code : 1");
            promoList.add(item);
        }
        adapter = new OfferListAdapter(getContext(), promoList);
        mRecyclerView.setAdapter(adapter);
        return inflateView;
    }

}

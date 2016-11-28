package com.beaconpoc.ReservationNotification.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beaconpoc.ReservationNotification.MyApplication;
import com.beaconpoc.ReservationNotification.adapter.OfferListAdapter;
import com.beaconpoc.ReservationNotification.R;
import com.beaconpoc.ReservationNotification.model.OfferItem;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationFcmModel;
import com.beaconpoc.ReservationNotification.webservice.model.RulesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aparupa on 11/18/2016.
 */

public class OfferListFragment extends BaseFragment {

    private List<OfferItem> promoList;
    private RecyclerView mRecyclerView;
    private OfferListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflateView = inflater.inflate(R.layout.offerlist, container, false);
        mRecyclerView = (RecyclerView) inflateView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        promoList = new ArrayList<>();
        if(((MyApplication)getActivity().getApplication()).getPushNotificationFcmModel() != null) {
            PushNotificationFcmModel pushNotificationFcmModel = ((MyApplication) getActivity().getApplication()).getPushNotificationFcmModel();
            List<RulesModel> rulesModels = pushNotificationFcmModel.getReservations().get(0).getPromoOffers().getRules();
            for (int i = 0; i < rulesModels.size(); i++) {
                OfferItem item = new OfferItem();
                item.setTitle(rulesModels.get(i).getCategory()
                        + " @"
                        + rulesModels.get(i).getDiscount()
                        + " "
                        + rulesModels.get(i).getDiscountType()
                        + " discount");
                item.setDescription(rulesModels.get(i).getDescription());
                item.setPromocode("Promo Code : " + rulesModels.get(i).getPromoCodeId());
                promoList.add(item);
            }
        }
        adapter = new OfferListAdapter(getContext(), promoList);
        mRecyclerView.setAdapter(adapter);
        return inflateView;
    }

}

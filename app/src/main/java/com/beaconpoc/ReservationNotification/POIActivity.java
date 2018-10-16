package com.beaconpoc.ReservationNotification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beaconpoc.ReservationNotification.adapter.OfferListAdapter;
import com.beaconpoc.ReservationNotification.constant.AppBarType;
import com.beaconpoc.ReservationNotification.model.OfferItem;
import com.beaconpoc.ReservationNotification.webservice.model.PointOfInterestModel;
import com.beaconpoc.ReservationNotification.webservice.model.PushNotificationFcmModel;
import com.beaconpoc.ReservationNotification.webservice.model.RulesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aparupa on 24-11-2016.
 */

public class POIActivity extends BaseActivity {

    private List<OfferItem> POIList;
    private RecyclerView mRecyclerView;
    private OfferListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerlist);
        setupAppBar(getString(R.string.poi_header), AppBarType.BACK_ARROW);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        POIList = new ArrayList<>();
        if(((MyApplication) getApplication()).getPushNotificationFcmModel() != null) {
            PushNotificationFcmModel pushNotificationFcmModel = ((MyApplication) getApplication()).getPushNotificationFcmModel();
            List<PointOfInterestModel> pointOfInterestModel = pushNotificationFcmModel.getPoi();
            for (int i = 0; i < pointOfInterestModel.size(); i++) {
                OfferItem item = new OfferItem();
                item.setTitle(pointOfInterestModel.get(i).getName());
                item.setDescription(pointOfInterestModel.get(i).getSpecialInstructions());
                if(pointOfInterestModel.get(i).getPromoOffers() != null) {
                    item.setPromocode("Loyalty Code : " + pointOfInterestModel.get(i).getPromoOffers().getLoyaltyCode());
                }
                POIList.add(item);
            }
        }
        adapter = new OfferListAdapter(this, POIList);
        mRecyclerView.setAdapter(adapter);

    }
}

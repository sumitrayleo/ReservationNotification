package com.beaconpoc.ReservationNotification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beaconpoc.ReservationNotification.adapter.OfferListAdapter;
import com.beaconpoc.ReservationNotification.constant.AppBarType;
import com.beaconpoc.ReservationNotification.model.OfferItem;
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
                POIList.add(item);
            }
        }
        adapter = new OfferListAdapter(this, POIList);
        mRecyclerView.setAdapter(adapter);

    }
}

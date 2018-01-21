package com.ordinefacile.root.ordinefacile.data.network;

import com.ordinefacile.root.ordinefacile.data.network.model.Store;

import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public class AppApiHelper implements ApiHelper{

    @Override
    public Observable<Store> getStoreDetails(String qrCode) {
        final API apiService = APIClient.getClient().create(API.class);

        return apiService.getStoreDetails(qrCode);
    }
}

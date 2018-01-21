package com.ordinefacile.root.ordinefacile.data.network;
import com.ordinefacile.root.ordinefacile.data.network.model.Store;
import rx.Observable;

/**
 * Created by root on 1/20/18.
 */

public interface ApiHelper {

      Observable<Store> getStoreDetails(String qrCode);

}

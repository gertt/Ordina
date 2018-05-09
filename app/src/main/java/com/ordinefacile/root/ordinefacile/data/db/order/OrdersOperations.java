package com.ordinefacile.root.ordinefacile.data.db.order;

import java.util.List;

import rx.Observable;

/**
 * Created by Eljo on 2/7/2018.
 */

public interface OrdersOperations {


    Observable create(Orders p);




    Observable<List<Orders>> read();

    boolean update(Orders p);

    boolean delete(Orders p);

    Observable delete2(int p);

    boolean checkIfExist(Orders p);



}

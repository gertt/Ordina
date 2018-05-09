package com.ordinefacile.root.ordinefacile.data.db.push_history;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.order.Orders;

import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;

/**
 * Created by user on 4/26/2018.
 */

public class PushHistoryOperationsImp  implements PushHistoryOperations {

    DatabaseHelper helper;
    RuntimeExceptionDao<PushHistory, Integer> pushdao;

    public PushHistoryOperationsImp(Context contex) {

        helper = new DatabaseHelper(contex);
        pushdao = helper.getRuntimeExceptionDao(PushHistory.class);

    }

    ///////////// PUSH-HISTORY \\\\\\\\\\\\
    @Override
    public Observable<Integer> insertPush(PushHistory p) {

        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int integer = helper.getPushdao().create(p);
                return integer;
            }
        });

    }


    @Override
    public Observable<List<PushHistory>> read() {

        return Observable.fromCallable(new Callable<List<PushHistory>>() {
            @Override
            public List<PushHistory> call() throws Exception {
                List<PushHistory> pushList = pushdao.queryForAll();
                Gson gson = new GsonBuilder().create();

                return pushList;
            }
        });
    }


}


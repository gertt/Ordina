package com.ordinefacile.root.ordinefacile.ui.push_history;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistory;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistoryOperationsImp;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 4/26/2018.
 */


public class PushHistoryPresenter {

    Context context;
    Context mcontext;
    PushHistoryOperationsImp pushHistoryOperationsImp;
    PushHistory pushHistory;
    DatabaseHelper databaseHelper;
    RuntimeExceptionDao<PushHistory, Integer> userDao;
    PushHistoryActivity pushHistoryActivity;
    List<PushHistory> feedItemList;


    public PushHistoryPresenter(Context context, PushHistoryActivity pushHistoryActivity) {

        this.context = context;
        pushHistoryOperationsImp = new PushHistoryOperationsImp(context);
        pushHistory = new PushHistory();
        databaseHelper = new DatabaseHelper(context);
        userDao = databaseHelper.getRuntimeExceptionDao(PushHistory.class);
        this.pushHistoryActivity = pushHistoryActivity;

    }

    public PushHistoryPresenter(Context mcontext) {
        this.mcontext = mcontext;
        pushHistoryOperationsImp = new PushHistoryOperationsImp(mcontext);
        pushHistory = new PushHistory();
        databaseHelper = new DatabaseHelper(mcontext);
        userDao = databaseHelper.getRuntimeExceptionDao(PushHistory.class);
    }

    public void getListPush() {
        pushHistoryOperationsImp.read().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PushHistory>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("", "");

                        pushHistoryActivity.listAdapter(feedItemList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");

                    }

                    @Override
                    public void onNext(List<PushHistory> pushHistories) {
                        Log.d("", "");

                        feedItemList = new ArrayList<PushHistory>();
                        for (int i = 0; i < pushHistories.size(); i++) {

                            String ss = pushHistories.get(i).getDescriptions();
                            feedItemList.add(pushHistories.get(i));

                        }

                    }
                });
    }

    public void inserData(String title, String description, Float price){

       pushHistory.setTittle("Title: "+title);
       pushHistory.setDescriptions("Description: "+description);
       pushHistory.setPrice("Price: "+price.toString()+" €");

        pushHistoryOperationsImp.insertPush(pushHistory).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer> () {
                    @Override
                    public void onCompleted() {
                        Log.d("", "");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                    @Override
                    public void onNext(Integer push) {

                    }

                });

    }
}

package com.ordinefacile.root.ordinefacile.data.db.order;

import android.content.Context;
import android.database.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.table.TableUtils;
import com.ordinefacile.root.ordinefacile.data.db.DatabaseHelper;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistory;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;

/**
 * Created by Eljo on 2/7/2018.
 */

public class OrdersOperationsImp implements OrdersOperations {

    DatabaseHelper helper;
    RuntimeExceptionDao<Orders, Integer> userDao;
    RuntimeExceptionDao<PushHistory, Integer> pushdao;

    public OrdersOperationsImp(Context contex) {
        helper = new DatabaseHelper(contex);
        userDao = helper.getRuntimeExceptionDao(Orders.class);

    }


///////////// ORDERS \\\\\\\\\\\\
    @Override
    public Observable<Integer> create(Orders p) {

        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int integer = helper.getUserDao().create(p);
                return integer;
            }
        });

    }
    ///////////// ORDERS \\\\\\\\\\\\
    @Override
    public Observable<List<Orders>> read() {

        return Observable.fromCallable(new Callable<List<Orders>>() {
            @Override
            public List<Orders> call() throws Exception {
                List<Orders> studentList = userDao.queryForAll();
                Gson gson = new GsonBuilder().create();

                return studentList;
            }
        });
    }
    ///////////// ORDERS \\\\\\\\\\\\
    @Override
    public boolean update(Orders p) {
        if(checkIfExist(p) == true){
            UpdateBuilder<Orders, Integer> updateBuilder = userDao.updateBuilder();

            try {
                updateBuilder.where().eq("first_name", p.getmId());
                updateBuilder.updateColumnValue("first_name" , "Your name changed to Eljo");

                updateBuilder.update();
                return true;
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                return false;

            }
        }
        return false;
    }
    ///////////// ORDERS \\\\\\\\\\\\
    @Override
    public boolean delete(Orders p) {
        if(checkIfExist(p) == true){
            DeleteBuilder<Orders, Integer> deleteBuilder = userDao.deleteBuilder();
            try {
                deleteBuilder.where().eq("first_name", p.getmId());
                deleteBuilder.delete();
                return true;
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
    ///////////// ORDERS \\\\\\\\\\\\
    @Override
    public boolean checkIfExist(Orders p) {
        List<Orders> results = null;
        try {
            results = userDao.queryBuilder().where().eq("name",p.getmId()).query();


        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
        if(results.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    ///////////// ORDERS \\\\\\\\\\\\
    @Override
    public Observable<DeleteBuilder<Orders, Integer>>delete2(int d){

        return Observable.fromCallable(new Callable<DeleteBuilder<Orders, Integer>>(){
            public DeleteBuilder<Orders, Integer> call() throws Exception {
                DeleteBuilder<Orders, Integer> deleteBuilder = userDao.deleteBuilder();
                deleteBuilder.where().eq("id_product_cart", d);
                deleteBuilder.delete();
                return deleteBuilder;
            }
        });

    }


    @Override
    public Observable<DeleteBuilder<Orders, Integer>>deleteall(int d){

        return Observable.fromCallable(new Callable<DeleteBuilder<Orders, Integer>>(){
            public DeleteBuilder<Orders, Integer> call() throws Exception {
                DeleteBuilder<Orders, Integer> deleteBuilder = userDao.deleteBuilder();
                deleteBuilder.delete();
                return deleteBuilder;
            }
        });

    }
}

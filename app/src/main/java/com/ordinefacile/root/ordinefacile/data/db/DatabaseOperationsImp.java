package com.ordinefacile.root.ordinefacile.data.db;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;

/**
 * Created by Eljo on 2/7/2018.
 */

public class DatabaseOperationsImp implements DatabaseOperations{

    DatabaseHelper helper;
    RuntimeExceptionDao<Orders, Integer> userDao;

    public DatabaseOperationsImp(Context contex) {
        helper = new DatabaseHelper(contex);
        userDao = helper.getRuntimeExceptionDao(Orders.class);

    }

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

    @Override
    public Observable<List<Orders>> read() {

        return Observable.fromCallable(new Callable<List<Orders>>() {
            @Override
            public List<Orders> call() throws Exception {
                List<Orders> studentList = userDao.queryForAll();
                return studentList;
            }
        });
    }

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

    @Override
    public Observable<DeleteBuilder<Orders, Integer>>delete2(int d){

        return Observable.fromCallable(new Callable<DeleteBuilder<Orders, Integer>>(){
            public DeleteBuilder<Orders, Integer> call() throws Exception {
                DeleteBuilder<Orders, Integer> deleteBuilder = userDao.deleteBuilder();
                deleteBuilder.where().eq("id_product", d);
                deleteBuilder.delete();
                return deleteBuilder;
            }
        });

    }
}

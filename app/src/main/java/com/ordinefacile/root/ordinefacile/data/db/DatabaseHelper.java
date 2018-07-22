package com.ordinefacile.root.ordinefacile.data.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ordinefacile.root.ordinefacile.data.db.order.Orders;
import com.ordinefacile.root.ordinefacile.data.db.push_history.PushHistory;

/**
 * Created by Eljo on 2/4/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "ordinafacile.db";
    private static final int    DATABASE_VERSION = 26;

    private Dao<Orders, Integer> mUserDao = null;
    private Dao<PushHistory, Integer> pushdao = null;

    public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, Orders.class);
            TableUtils.createTable(connectionSource, PushHistory.class);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            TableUtils.dropTable(connectionSource, Orders.class, true);
            TableUtils.dropTable(connectionSource, PushHistory.class, true);

            onCreate(db, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /* Orders */
    public Dao<Orders, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            try {

                mUserDao = getDao(Orders.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        return mUserDao;
    }

    /* PushHistoryActivity */
    public Dao<PushHistory, Integer> getPushdao() throws SQLException {
        if (pushdao == null) {
            try {

                pushdao = getDao(PushHistory.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        return pushdao;
    }

    @Override
    public void close() {
        mUserDao = null;
        pushdao  = null;
        super.close();
    }
}


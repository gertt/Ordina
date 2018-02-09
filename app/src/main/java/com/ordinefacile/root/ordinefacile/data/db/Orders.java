package com.ordinefacile.root.ordinefacile.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Eljo on 2/4/2018.
 */


@DatabaseTable(tableName = Orders.TABLE_NAME_USERS)
public class Orders {

    public static final String TABLE_NAME_USERS = "Orders";
    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_QUANTITY_NAME   = "quantity";
    public static final String FIELD_REFERENCE_ID_NAME   = "reference_id";
    public static final String FIELD_USER_ORDER   = "user_order";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true, unique = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_QUANTITY_NAME)
    private String mQuantity;

    @DatabaseField(columnName = FIELD_REFERENCE_ID_NAME)
    private String mReferenceID;

    @DatabaseField(columnName = FIELD_USER_ORDER)
    private String mUserOrder;


    public Orders() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public void setmReferenceID(String mReferenceID) {
        this.mReferenceID = mReferenceID;
    }

    public void setmUserOrder(String mUserOrder) {
        this.mUserOrder = mUserOrder;
    }

    /** Getters & Setters **/

    public static String getFieldNameId() {
        return FIELD_NAME_ID;
    }
    public static String getFieldNameName() {
        return FIELD_NAME_NAME;
    }
    public static String getFieldNameQuantity() {
        return FIELD_QUANTITY_NAME;
    }
    public static String getFieldReferenceIdName() {
        return FIELD_REFERENCE_ID_NAME;
    }

    public static String getFieldUserOrder() {
        return FIELD_USER_ORDER;
    }



    public int getmId() {
        return mId;
    }
    public String getmName() {
        return mName;
    }
    public String getmQuantity() {
        return mQuantity;
    }
    public String getmReferenceID() {
        return mReferenceID;
    }

    public String getmUserOrder() {
        return mUserOrder;
    }

}
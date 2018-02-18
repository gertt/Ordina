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
    public static final String FIELD_DESCRIPTION   = "descriptions";
    public static final String FIELD_USER_ORDER   = "user_order";
    public static final String FIELD_PRICE   = "price";
    public static final String FIELD_METRIC   = "metric";
    public static final String FIELD_URLIMAGE   = "url_image";
    public static final String FIELD_DESCR   = "descr";
    public static final String FIELD_FINAL_PRICE   = "final_price";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true, unique = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_QUANTITY_NAME)
    private Float mQuantity;

    @DatabaseField(columnName = FIELD_DESCRIPTION)
    private String mDescriptions;

    @DatabaseField(columnName = FIELD_USER_ORDER)
    private String mUserOrder;

    @DatabaseField(columnName = FIELD_PRICE)
    private Float mPrice;

    @DatabaseField(columnName = FIELD_METRIC)
    private String mMetric;

    @DatabaseField(columnName = FIELD_URLIMAGE)
    private String mUrl_Image ;

    @DatabaseField(columnName = FIELD_DESCR)
    private String mDescr ;

    @DatabaseField(columnName = FIELD_FINAL_PRICE)
    private float mFinalPrice ;



    public Orders() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public void setmQuantity(Float mQuantity) {
        this.mQuantity = mQuantity;
    }

    public void setmDescriptions(String mDescriptions) {
        this.mDescriptions = mDescriptions;
    }

    public void setmUserOrder(String mUserOrder) {
        this.mUserOrder = mUserOrder;
    }

    public void setmPrice(Float mPrice) {
        this.mPrice = mPrice;
    }

    public void setmMetric(String mMetric) {
        this.mMetric = mMetric;
    }

    public void setmUrl_Image(String mUrl_Image) {
        this.mUrl_Image = mUrl_Image;
    }

    public void setmDescr(String mDescr) {
        this.mDescr = mDescr;
    }

    public void setmFinalPrice(Float mFinalPrice) {
        this.mFinalPrice = mFinalPrice;
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
    public static String getFieldDescription() {
        return FIELD_DESCRIPTION;
    }

    public static String getFieldUserOrder() {
        return FIELD_USER_ORDER;
    }

    public static String getFieldPrice() {
        return FIELD_PRICE;
    }

    public static String getFieldMetric() {
        return FIELD_METRIC;
    }

    public static String getFieldUrlimage() {
        return FIELD_URLIMAGE;
    }
    public static String getFieldDescr() {
        return FIELD_DESCR;
    }
    public static String getFieldFinalPrice() {
        return FIELD_FINAL_PRICE;
    }

    public int getmId() {
        return mId;
    }
    public String getmName() {
        return mName;
    }
    public Float getmQuantity() {
        return mQuantity;
    }
    public String getmDescriptions() {
        return mDescriptions;
    }

    public String getmUserOrder() {
        return mUserOrder;
    }


    public Float getmPrice() {
        return mPrice;
    }

    public String getmMetric() {
        return mMetric;
    }

    public String getmUrl_Image() {
        return mUrl_Image;
    }
    public String getmDescr() {
        return mDescr;
    }

    public Float getmFinalPrice() {
        return mFinalPrice;
    }
}
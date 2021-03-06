package com.ordinefacile.root.ordinefacile.data.db.order;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Eljo on 2/4/2018.
 */

@DatabaseTable(tableName = Orders.TABLE_NAME_USERS) public class Orders {

    public static final String TABLE_NAME_USERS = "Orders";
    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_QUANTITY_NAME   = "quantity";
    public static final String FIELD_DESCRIPTION   = "descriptions";
    public static final String FIELD_PRICE   = "price";
    public static final String FIELD_METRIC   = "metric";
    public static final String FIELD_URLIMAGE   = "url_image";
    public static final String FIELD_DESCR   = "descr";
    public static final String FIELD_FINAL_PRICE   = "final_price";
    public static final String FIELD_ID_TABLE   = "id_table";
    public static final String FIELD_ID_PRODUCT   = "id_product";
    public static final String FIELD_ID_PRODUCT_CART   = "id_product_cart";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true, unique = true)  private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)  private String mName;

    @DatabaseField(columnName = FIELD_QUANTITY_NAME)  private int mQuantity;

    @DatabaseField(columnName = FIELD_DESCRIPTION)  private String mDescriptions;

    @DatabaseField(columnName = FIELD_PRICE) private Float mPrice;

    @DatabaseField(columnName = FIELD_METRIC) private String mMetric;

    @DatabaseField(columnName = FIELD_URLIMAGE)  private String mUrl_Image ;

    @DatabaseField(columnName = FIELD_DESCR)   private String mDescr ;

    @DatabaseField(columnName = FIELD_FINAL_PRICE)  private float mFinalPrice ;

    @DatabaseField(columnName = FIELD_ID_TABLE)  private String mIdTable ;

    @DatabaseField(columnName = FIELD_ID_PRODUCT)  private String mIdProduct ;

    @DatabaseField(columnName = FIELD_ID_PRODUCT_CART)  private String mIdProductCart ;

    public Orders() {
        // Don't forget the empty constructor, needed by ORMLite.
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public void setmDescriptions(String mDescriptions) {
        this.mDescriptions = mDescriptions;
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

    public void setmIdTable(String mIdTable) {
        this.mIdTable = mIdTable;
    }

    public void setmIdProduct(String mIdProduct) {
        this.mIdProduct = mIdProduct;
    }

    public void setmIdProductCart(String mIdProductCart) {
        this.mIdProductCart = mIdProductCart;
    }
    /** Getters **/

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

    public static String getFieldIdTable() {
        return FIELD_ID_TABLE;
    }

    public static String getFieldIdProduct() {
        return FIELD_ID_PRODUCT;
    }

    public static String getFieldIdProductCart() {
        return FIELD_ID_PRODUCT_CART;
    }

    /** Setters **/

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public String getmDescriptions() {
        return mDescriptions;
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

    public String getmIdTable() {
        return mIdTable;
    }

    public String getmIdProduct() {
        return mIdProduct;
    }

    public String getmIdProductCart() {
        return mIdProductCart;
    }

}
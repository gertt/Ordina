package com.ordinefacile.root.ordinefacile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eljo on 3/25/2018.
 */

public class JsonObejctOrderItem {

    @SerializedName("mDescriptions")
    @Expose
    private String mDescriptions;
    @SerializedName("mFinalPrice")
    @Expose
    private Double mFinalPrice;
    @SerializedName("mId")
    @Expose
    private Integer mId;
    @SerializedName("mIdProduct")
    @Expose
    private String mIdProduct;
    @SerializedName("mIdTable")
    @Expose
    private String mIdTable;
    @SerializedName("mMetric")
    @Expose
    private String mMetric;
    @SerializedName("mName")
    @Expose
    private String mName;
    @SerializedName("mPrice")
    @Expose
    private Double mPrice;
    @SerializedName("mQuantity")
    @Expose
    private Integer mQuantity;
    @SerializedName("mUrl_Image")
    @Expose
    private String mUrlImage;

    public String getMDescriptions() {
        return mDescriptions;
    }

    public void setMDescriptions(String mDescriptions) {
        this.mDescriptions = mDescriptions;
    }

    public Double getMFinalPrice() {
        return mFinalPrice;
    }

    public void setMFinalPrice(Double mFinalPrice) {
        this.mFinalPrice = mFinalPrice;
    }

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    public String getMIdProduct() {
        return mIdProduct;
    }

    public void setMIdProduct(String mIdProduct) {
        this.mIdProduct = mIdProduct;
    }

    public String getMIdTable() {
        return mIdTable;
    }

    public void setMIdTable(String mIdTable) {
        this.mIdTable = mIdTable;
    }

    public String getMMetric() {
        return mMetric;
    }

    public void setMMetric(String mMetric) {
        this.mMetric = mMetric;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public Double getMPrice() {
        return mPrice;
    }

    public void setMPrice(Double mPrice) {
        this.mPrice = mPrice;
    }

    public Integer getMQuantity() {
        return mQuantity;
    }

    public void setMQuantity(Integer mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getMUrlImage() {
        return mUrlImage;
    }

    public void setMUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
    }

}

package com.ordinefacile.root.ordinefacile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 3/23/2018.
 */

public class OrderHistoryData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("store")
    @Expose
    private String store;
    @SerializedName("store_image")
    @Expose
    private String storeImage;
    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("total_items")
    @Expose
    private String totalItems;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("items")
    @Expose
    private List<OrderHistoryItem> items = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderHistoryItem> getItems() {
        return items;
    }

    public void setItems(List<OrderHistoryItem> items) {
        this.items = items;
    }


}
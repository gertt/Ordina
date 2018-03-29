package com.ordinefacile.root.ordinefacile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eljo on 3/25/2018.
 */

public class JsonObejctClass {

    @SerializedName("table_id")
    @Expose
    private String tableId;
    @SerializedName("order_items")
    @Expose
    private List<JsonObejctOrderItem> orderItems = null;
    @SerializedName("device")
    @Expose
    private JsonObejctDevice device;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public List<JsonObejctOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<JsonObejctOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public JsonObejctDevice getDevice() {
        return device;
    }

}

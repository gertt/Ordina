package com.ordinefacile.root.ordinefacile.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 1/20/18.
 */

public class Data {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("qr_code")
    @Expose
    private String qrCode;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("social_fcb")
    @Expose
    private Object socialFcb;
    @SerializedName("social_goo")
    @Expose
    private Object socialGoo;
    @SerializedName("social_insta")
    @Expose
    private Object socialInsta;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getSocialFcb() {
        return socialFcb;
    }

    public void setSocialFcb(Object socialFcb) {
        this.socialFcb = socialFcb;
    }

    public Object getSocialGoo() {
        return socialGoo;
    }

    public void setSocialGoo(Object socialGoo) {
        this.socialGoo = socialGoo;
    }

    public Object getSocialInsta() {
        return socialInsta;
    }

    public void setSocialInsta(Object socialInsta) {
        this.socialInsta = socialInsta;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

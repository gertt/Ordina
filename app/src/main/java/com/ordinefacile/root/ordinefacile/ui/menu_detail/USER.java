package com.ordinefacile.root.ordinefacile.ui.menu_detail;

/**
 * Created by user on 3/15/2018.
 */

public class USER {
    public USER(String name, int age, int avatarRes, String phone) {
        this.name = name;
        this.age = age;
        this.avatarRes = avatarRes;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public void setAvatarRes(int avatarRes) {
        this.avatarRes = avatarRes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String name;
    private int age;
    private int avatarRes;
    private String phone;
}

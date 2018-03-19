package com.ordinefacile.root.ordinefacile.ui.my_order;

/**
 * Created by user on 3/19/2018.
 */

class Eventlist {

    private  String emri;
    private  String mbiemri;

    public Eventlist(String emri, String mbiemri) {
        this.emri = emri;
        this.mbiemri = mbiemri;
    }

    public Eventlist() {
    }
    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }
}






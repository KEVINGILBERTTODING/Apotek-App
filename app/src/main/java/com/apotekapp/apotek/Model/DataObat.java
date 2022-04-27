package com.apotekapp.apotek.Model;

public class DataObat {
    private String id, kdobat, nmobat, satuan, jumlah, desc, expired;

    public DataObat() {
    }

    public DataObat(String id, String kdobat, String nmobat, String satuan, String jumlah, String desc, String expired) {
        this.id = id;
        this.kdobat = kdobat;
        this.nmobat= nmobat;
        this.satuan = satuan;
        this.jumlah = jumlah;
        this.desc = desc;
        this.expired = expired;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKdobat() {
        return kdobat;
    }

    public void setKdobat(String kdobat) {
        this.kdobat = kdobat;
    }

    public String getNmobat() {
        return nmobat;
    }

    public void setNmobat(String nmobat) {
        this.nmobat = nmobat;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}

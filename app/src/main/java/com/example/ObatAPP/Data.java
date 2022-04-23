package com.example.ObatAPP;

public class Data {
    private String id, kdobat, nmobat, satuan, jumlah, expired;

    public Data() {
    }

    public Data(String id, String kdobat, String nmobat, String satuan, String jumlah, String expired) {
        this.id = id;
        this.kdobat = kdobat;
        this.nmobat= nmobat;
        this.satuan = satuan;
        this.jumlah = jumlah;
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

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}

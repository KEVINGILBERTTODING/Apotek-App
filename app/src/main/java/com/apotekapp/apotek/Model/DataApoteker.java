package com.apotekapp.apotek.Model;

public class DataApoteker {

    private String id, id_apoteker, nm_apoteker, kota_apoteker, noHp_apoteker, shift, alamat_apoteker;

    public DataApoteker() {

    }

    public DataApoteker(String id, String id_apoteker, String nm_apoteker, String kota_apoteker, String noHp_apoteker, String shift, String alamat_apoteker) {

        this.id                 =   id;
        this.id_apoteker        =   id_apoteker;
        this.nm_apoteker        =   nm_apoteker;
        this.kota_apoteker      =   kota_apoteker;
        this.noHp_apoteker      =   noHp_apoteker;
        this.shift              =   shift;
        this.alamat_apoteker    =   alamat_apoteker;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_apoteker() {
        return id_apoteker;
    }

    public void setId_apoteker(String id_apoteker) {
        this.id_apoteker = id_apoteker;
    }

    public String getNm_apoteker() {
        return nm_apoteker;
    }

    public void setNm_apoteker(String nm_apoteker) {
        this.nm_apoteker = nm_apoteker;
    }

    public String getKota_apoteker() {
        return kota_apoteker;
    }

    public void setKota_apoteker(String kota_apoteker) {
        this.kota_apoteker = kota_apoteker;
    }

    public String getNoHp_apoteker() {
        return noHp_apoteker;
    }

    public void setNoHp_apoteker(String noHp_apoteker) {
        this.noHp_apoteker = noHp_apoteker;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getAlamat_apoteker() {
        return alamat_apoteker;
    }

    public void setAlamat_apoteker(String alamat_apoteker) {
        this.alamat_apoteker = alamat_apoteker;
    }
}

package com.example.mysertifikasi;

public class DetailFLow {

    private String id_detail, keterangan, tanggal, status;
    private Double nominal;
    private int panah;

    public DetailFLow(String id_detail, Double nominal, String keterangan, String tanggal,
                        String status, int panah){
        this.id_detail = id_detail;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.status = status;
        this.panah = panah;
    }

    public String getId_detail() {
        return id_detail;
    }

    public void setId_detail(String id_detail) {
        this.id_detail = id_detail;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPanah() {
        return panah;
    }

    public void setPanah(int panah) {
        this.panah = panah;
    }
}

package com.its.bigstarsapp.Models;

public class BayarFee {
    String id_bayar_fee, waktu, total_pertemuan, total_harga_fee;
    String id_pengajar, nama_pengajar;
    String id_admin;

    public String getId_bayar_fee() {
        return id_bayar_fee;
    }

    public void setId_bayar_fee(String id_bayar_fee) {
        this.id_bayar_fee = id_bayar_fee;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTotal_pertemuan() {
        return total_pertemuan;
    }

    public void setTotal_pertemuan(String total_pertemuan) {
        this.total_pertemuan = total_pertemuan;
    }

    public String getTotal_harga_fee() {
        return total_harga_fee;
    }

    public void setTotal_harga_fee(String total_harga_fee) {
        this.total_harga_fee = total_harga_fee;
    }

    public String getId_pengajar() {
        return id_pengajar;
    }

    public void setId_pengajar(String id_pengajar) {
        this.id_pengajar = id_pengajar;
    }

    public String getNama_pengajar() {
        return nama_pengajar;
    }

    public void setNama_pengajar(String nama_pengajar) {
        this.nama_pengajar = nama_pengajar;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }
}

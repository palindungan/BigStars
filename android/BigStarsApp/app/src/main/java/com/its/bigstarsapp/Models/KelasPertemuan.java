package com.its.bigstarsapp.Models;

public class KelasPertemuan {

    String id_kelas_pertemuan, hari, jam_mulai, jam_berakhir, harga_fee, harga_spp, id_sharing, nama_sharing, status_data;
    String id_mata_pelajaran, nama_mata_pelajaran;
    String id_pengajar, nama_pengajar;
    String jumlah_murid;

    public String getId_kelas_pertemuan() {
        return id_kelas_pertemuan;
    }

    public void setId_kelas_pertemuan(String id_kelas_pertemuan) {
        this.id_kelas_pertemuan = id_kelas_pertemuan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_berakhir() {
        return jam_berakhir;
    }

    public void setJam_berakhir(String jam_berakhir) {
        this.jam_berakhir = jam_berakhir;
    }

    public String getHarga_fee() {
        return harga_fee;
    }

    public void setHarga_fee(String harga_fee) {
        this.harga_fee = harga_fee;
    }

    public String getHarga_spp() {
        return harga_spp;
    }

    public void setHarga_spp(String harga_spp) {
        this.harga_spp = harga_spp;
    }

    public String getId_sharing() {
        return id_sharing;
    }

    public void setId_sharing(String id_sharing) {
        this.id_sharing = id_sharing;
    }

    public String getNama_sharing() {
        return nama_sharing;
    }

    public void setNama_sharing(String nama_sharing) {
        this.nama_sharing = nama_sharing;
    }

    public String getStatus_data() {
        return status_data;
    }

    public void setStatus_data(String status_data) {
        this.status_data = status_data;
    }

    public String getId_mata_pelajaran() {
        return id_mata_pelajaran;
    }

    public void setId_mata_pelajaran(String id_mata_pelajaran) {
        this.id_mata_pelajaran = id_mata_pelajaran;
    }

    public String getNama_mata_pelajaran() {
        return nama_mata_pelajaran;
    }

    public void setNama_mata_pelajaran(String nama_mata_pelajaran) {
        this.nama_mata_pelajaran = nama_mata_pelajaran;
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

    public String getJumlah_murid() {
        return jumlah_murid;
    }

    public void setJumlah_murid(String jumlah_murid) {
        this.jumlah_murid = jumlah_murid;
    }
}

package com.its.bigstarsapp.Models;

public class Murid {
    String id_murid, id_wali_murid, nama, nama_wali_murid, alamat, foto;
    String id_kelas_pertemuan, id_kelas_pertemuan_detail;

    public String getId_murid() {
        return id_murid;
    }

    public void setId_murid(String id_murid) {
        this.id_murid = id_murid;
    }

    public String getId_wali_murid() {
        return id_wali_murid;
    }

    public void setId_wali_murid(String id_wali_murid) {
        this.id_wali_murid = id_wali_murid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_wali_murid() {
        return nama_wali_murid;
    }

    public void setNama_wali_murid(String nama_wali_murid) {
        this.nama_wali_murid = nama_wali_murid;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId_kelas_pertemuan() {
        return id_kelas_pertemuan;
    }

    public void setId_kelas_pertemuan(String id_kelas_pertemuan) {
        this.id_kelas_pertemuan = id_kelas_pertemuan;
    }

    public String getId_kelas_pertemuan_detail() {
        return id_kelas_pertemuan_detail;
    }

    public void setId_kelas_pertemuan_detail(String id_kelas_pertemuan_detail) {
        this.id_kelas_pertemuan_detail = id_kelas_pertemuan_detail;
    }
}

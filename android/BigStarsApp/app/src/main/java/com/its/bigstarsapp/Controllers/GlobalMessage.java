package com.its.bigstarsapp.Controllers;

public class GlobalMessage {

    String messageConnectionError;
    String messageResponseError;

    String validasiNamaKosong;
    String validasiUsernameKosong;
    String validasiPasswordKosong;
    String validasiAlamatKosong;
    String validasiNoHpKosong;

    String validasiKonfirmasiPasswordSalah;

    String validasiPilihSalahSatuData;
    String validasiPilihDataWaliMurid;

    String ya;
    String tidak;

    String validasiUpdateData;
    String validasiHapusData;
    String validasiAddData;

    String pilihYaUpdateData;
    String pilihYaHapusData;
    String pilihYaAddData;

    String pilihGambar;

    String errorUpdateData;
    String errorHapusData;
    String errorAddData;
    String errorLoadingGambar;

    public GlobalMessage() {
        messageConnectionError = "Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda";
        messageResponseError = "Kesalahan Menerima Data : ";

        validasiNamaKosong = "Isi Nama Dengan Lengkap";
        validasiUsernameKosong = "Isi Username Dengan Lengkap";
        validasiPasswordKosong = "Isi Password Dengan Lengkap";
        validasiAlamatKosong = "Isi Alamat Dengan Lengkap";
        validasiNoHpKosong = "Isi No Hp Dengan Lengkap";

        validasiKonfirmasiPasswordSalah = "Konfirmasi Password Salah !";

        validasiPilihSalahSatuData = "Pilih Salah Satu Data !";
        validasiPilihDataWaliMurid = "Pilih Data Wali Murid !";

        ya = "Ya";
        tidak = "Tidak";

        validasiUpdateData = "Ingin Mengupdate Data ?";
        validasiHapusData = "Ingin Menghapus Data ";
        validasiAddData = "Ingin Menambah Data ?";

        pilihYaUpdateData = "Klik Ya untuk melakukan update !";
        pilihYaHapusData = "Klik Ya untuk melakukan hapus !";
        pilihYaAddData = "Klik Ya untuk menambah data !";

        pilihGambar = "Pilih Gambar";

        errorUpdateData = "Terjadi Kesalahan Update : ";
        errorHapusData = "Terjadi Kesalahan Hapus : ";
        errorAddData = "Terjadi Kesalahan Menambah Data : ";
        errorLoadingGambar = "Error, pilih gambar lainnya !";
    }

    public String getMessageConnectionError() {
        return messageConnectionError;
    }

    public String getMessageResponseError() {
        return messageResponseError;
    }

    public String getValidasiNamaKosong() {
        return validasiNamaKosong;
    }

    public String getValidasiUsernameKosong() {
        return validasiUsernameKosong;
    }

    public String getValidasiPasswordKosong() {
        return validasiPasswordKosong;
    }

    public String getValidasiAlamatKosong() {
        return validasiAlamatKosong;
    }

    public String getValidasiNoHpKosong() {
        return validasiNoHpKosong;
    }

    public String getValidasiKonfirmasiPasswordSalah() {
        return validasiKonfirmasiPasswordSalah;
    }

    public String getValidasiPilihSalahSatuData() {
        return validasiPilihSalahSatuData;
    }

    public String getValidasiPilihDataWaliMurid() {
        return validasiPilihDataWaliMurid;
    }

    public String getYa() {
        return ya;
    }

    public String getTidak() {
        return tidak;
    }

    public String getValidasiUpdateData() {
        return validasiUpdateData;
    }

    public String getValidasiHapusData() {
        return validasiHapusData;
    }

    public String getValidasiAddData() {
        return validasiAddData;
    }

    public String getPilihYaUpdateData() {
        return pilihYaUpdateData;
    }

    public String getPilihYaHapusData() {
        return pilihYaHapusData;
    }

    public String getPilihYaAddData() {
        return pilihYaAddData;
    }

    public String getPilihGambar() {
        return pilihGambar;
    }

    public String getErrorUpdateData() {
        return errorUpdateData;
    }

    public String getErrorHapusData() {
        return errorHapusData;
    }

    public String getErrorAddData() {
        return errorAddData;
    }

    public String getErrorLoadingGambar() {
        return errorLoadingGambar;
    }
}

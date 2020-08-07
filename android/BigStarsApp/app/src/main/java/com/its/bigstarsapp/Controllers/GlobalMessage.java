package com.its.bigstarsapp.Controllers;

public class GlobalMessage {

    String messageConnectionError;
    String messageResponseError;

    String validasiNamaKosong;
    String validasiUsernameKosong;
    String validasiPasswordKosong;

    String validasiKonfirmasiPasswordSalah;

    String ya;
    String tidak;

    String validasiUpdateDataAdmin;
    String pilihYaUpdateData;

    String validasiHapusDataMataPelajaran;
    String pilihYaHapusDataMataPelajaran;

    String errorHapusData;
    String errorUpdateData;
    String errorLoadingGambar;

    public GlobalMessage() {
        messageConnectionError = "Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda";
        messageResponseError = "Kesalahan Menerima Data : ";

        validasiNamaKosong = "Isi Nama Dengan Lengkap";
        validasiUsernameKosong = "Isi Username Dengan Lengkap";
        validasiPasswordKosong = "Isi Password Dengan Lengkap";

        validasiKonfirmasiPasswordSalah = "Konfirmasi Password Salah !";

        ya = "Ya";
        tidak = "Tidak";

        validasiUpdateDataAdmin = "Ingin Mengupdate Data Admin ?";
        pilihYaUpdateData = "Klik Ya untuk melakukan update !";

        validasiHapusDataMataPelajaran = "Ingin Menghapus Data Mata Pelajaran ";
        pilihYaHapusDataMataPelajaran = "Klik Ya untuk melakukan hapus !";

        errorHapusData = "Terjadi Kesalahan Hapus : ";
        errorUpdateData = "Terjadi Kesalahan Update : ";
        errorLoadingGambar = "Terjadi Kesalahan, pilih gambar lainnya !";
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

    public String getValidasiKonfirmasiPasswordSalah() {
        return validasiKonfirmasiPasswordSalah;
    }

    public String getYa() {
        return ya;
    }

    public String getTidak() {
        return tidak;
    }

    public String getValidasiUpdateDataAdmin() {
        return validasiUpdateDataAdmin;
    }

    public String getPilihYaUpdateData() {
        return pilihYaUpdateData;
    }

    public String getValidasiHapusDataMataPelajaran() {
        return validasiHapusDataMataPelajaran;
    }

    public String getPilihYaHapusDataMataPelajaran() {
        return pilihYaHapusDataMataPelajaran;
    }

    public String getErrorHapusData() {
        return errorHapusData;
    }

    public String getErrorUpdateData() {
        return errorUpdateData;
    }

    public String getErrorLoadingGambar() {
        return errorLoadingGambar;
    }
}

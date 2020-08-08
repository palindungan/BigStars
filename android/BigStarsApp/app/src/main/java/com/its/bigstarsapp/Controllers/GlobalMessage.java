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

    String validasiAddDataMataPelajaran;
    String pilihYaAddDataMataPelajaran;

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

        validasiKonfirmasiPasswordSalah = "Konfirmasi Password Salah !";

        ya = "Ya";
        tidak = "Tidak";

        validasiUpdateDataAdmin = "Ingin Mengupdate Data Admin ?";
        pilihYaUpdateData = "Klik Ya untuk melakukan update !";

        validasiHapusDataMataPelajaran = "Ingin Menghapus Data Mata Pelajaran ";
        pilihYaHapusDataMataPelajaran = "Klik Ya untuk melakukan hapus !";

        validasiAddDataMataPelajaran = "Ingin Menambah Data Mata Pelajaran ?";
        pilihYaAddDataMataPelajaran = "Klik Ya untuk menambah data !";

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

    public String getValidasiAddDataMataPelajaran() {
        return validasiAddDataMataPelajaran;
    }

    public String getPilihYaAddDataMataPelajaran() {
        return pilihYaAddDataMataPelajaran;
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

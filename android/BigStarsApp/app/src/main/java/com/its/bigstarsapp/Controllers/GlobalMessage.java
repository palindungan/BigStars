package com.its.bigstarsapp.Controllers;

public class GlobalMessage {

    String messageConnectionError;
    String messageResponseError;

    String validasiNamaKosong;
    String validasiUsernameKosong;
    String validasiPasswordKosong;

    String validasiKonfirmasiPasswordSalah;

    String validasiUpdateDataAdmin;

    String pilihYaUpdateData;

    public GlobalMessage() {
        messageConnectionError = "Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda";
        messageResponseError = "Kesalahan Menerima Data : ";

        validasiNamaKosong = "Isi Nama Dengan Lengkap";
        validasiUsernameKosong = "Isi Username Dengan Lengkap";
        validasiPasswordKosong = "Isi Password Dengan Lengkap";

        validasiKonfirmasiPasswordSalah = "Konfirmasi Password Salah !";

        validasiUpdateDataAdmin = "Ingin Mengupdate Data Admin ?";

        pilihYaUpdateData = "Klik Ya untuk melakukan update !";
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

    public String getValidasiUpdateDataAdmin() {
        return validasiUpdateDataAdmin;
    }

    public String getPilihYaUpdateData() {
        return pilihYaUpdateData;
    }
}

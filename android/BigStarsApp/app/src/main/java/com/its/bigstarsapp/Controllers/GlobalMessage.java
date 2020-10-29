package com.its.bigstarsapp.Controllers;

public class GlobalMessage {

    String messageConnectionError;
    String messageResponseError;

    String validasiNamaKosong;
    String validasiUsernameKosong;
    String validasiPasswordKosong;
    String validasiAlamatKosong;
    String validasiNoHpKosong;
    String validasiNamaMataPelajaranKosong;
    String validasiHariKosong;
    String validasiJamMulaiKosong;
    String validasiJamBerakhirKosong;
    String validasiHargaFeeKosong;
    String validasiHargaSppKosong;

    String validasiKonfirmasiPasswordSalah;

    String validasiPilihSalahSatuData;
    String validasiPilihDataWaliMurid;

    String ya;
    String tidak;

    String validasiUpdateData;
    String validasiHapusData;
    String validasiAddData;
    String validasiHapusSharing;
    String validasiMulaiAbsen;
    String validasiBatalAbsen;

    String pilihYaUpdateData;
    String pilihYaHapusData;
    String pilihYaAddData;
    String pilihYaMulaiAbsen;
    String pilihYaBatalAbsen;

    String pilihGambar;

    String errorUpdateData;
    String errorHapusData;
    String errorAddData;
    String errorLoadingGambar;
    String errorMulaiAbsen;
    String errorAmbilLokasi;
    String errorBatalAbsen;

    public GlobalMessage() {
        messageConnectionError = "Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda";
        messageResponseError = "Kesalahan Menerima Data : ";

        validasiNamaKosong = "Isi Nama Dengan Lengkap";
        validasiUsernameKosong = "Isi Username Dengan Lengkap";
        validasiPasswordKosong = "Isi Password Dengan Lengkap";
        validasiAlamatKosong = "Isi Alamat Dengan Lengkap";
        validasiNoHpKosong = "Isi No Hp Dengan Lengkap";
        validasiNamaMataPelajaranKosong = "Pilih Salah Satu Mata Pelajaran";
        validasiHariKosong = "Isi Hari Dengan Lengkap";
        validasiJamMulaiKosong = "Isi Jam Mulai Dengan Lengkap";
        validasiJamBerakhirKosong = "Isi Jam Berakhir Dengan Lengkap";
        validasiHargaFeeKosong = "Isi Harga Fee Dengan Lengkap";
        validasiHargaSppKosong = "Isi Harga Spp Dengan Lengkap";

        validasiKonfirmasiPasswordSalah = "Konfirmasi Password Salah !";

        validasiPilihSalahSatuData = "Pilih Salah Satu Data !";
        validasiPilihDataWaliMurid = "Pilih Data Wali Murid !";

        ya = "Ya";
        tidak = "Tidak";

        validasiUpdateData = "Ingin Mengupdate Data ?";
        validasiHapusData = "Ingin Menghapus Data ";
        validasiAddData = "Ingin Menambah Data ?";
        validasiHapusSharing = "Ingin Mengapus Sharing Kelas Pertemuan ?";
        validasiMulaiAbsen = "Mulai Absensi Kelas Pertemuan ?";
        validasiBatalAbsen = "Ingin Membatalkan Pertemuan ?";

        pilihYaUpdateData = "Klik Ya untuk melakukan update !";
        pilihYaHapusData = "Klik Ya untuk melakukan hapus !";
        pilihYaAddData = "Klik Ya untuk menambah data !";
        pilihYaMulaiAbsen = "Klik Ya untuk memulai absensi !";
        pilihYaBatalAbsen = "Klik Ya untuk Membatalkan Absensi !";

        pilihGambar = "Pilih Gambar";

        errorUpdateData = "Terjadi Kesalahan Update : ";
        errorHapusData = "Terjadi Kesalahan Hapus : ";
        errorAddData = "Terjadi Kesalahan Menambah Data : ";
        errorLoadingGambar = "Error, pilih gambar lainnya !";
        errorMulaiAbsen = "Terjadi Kesalahan Absensi : ";
        errorAmbilLokasi = "Ambil Lokasi Google Maps !";
        errorBatalAbsen = "Gagal membatalkan absensi !";
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

    public String getValidasiNamaMataPelajaranKosong() {
        return validasiNamaMataPelajaranKosong;
    }

    public String getValidasiHariKosong() {
        return validasiHariKosong;
    }

    public String getValidasiJamMulaiKosong() {
        return validasiJamMulaiKosong;
    }

    public String getValidasiJamBerakhirKosong() {
        return validasiJamBerakhirKosong;
    }

    public String getValidasiHargaFeeKosong() {
        return validasiHargaFeeKosong;
    }

    public String getValidasiHargaSppKosong() {
        return validasiHargaSppKosong;
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

    public String getValidasiHapusSharing() {
        return validasiHapusSharing;
    }

    public String getValidasiMulaiAbsen() {
        return validasiMulaiAbsen;
    }

    public String getValidasiBatalAbsen() {
        return validasiBatalAbsen;
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

    public String getPilihYaMulaiAbsen() {
        return pilihYaMulaiAbsen;
    }

    public String getPilihYaBatalAbsen() {
        return pilihYaBatalAbsen;
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

    public String getErrorMulaiAbsen() {
        return errorMulaiAbsen;
    }

    public String getErrorAmbilLokasi() {
        return errorAmbilLokasi;
    }

    public String getErrorBatalAbsen() {
        return errorBatalAbsen;
    }
}

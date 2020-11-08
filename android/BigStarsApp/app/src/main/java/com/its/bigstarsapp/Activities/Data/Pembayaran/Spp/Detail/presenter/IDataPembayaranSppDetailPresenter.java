package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter;

public interface IDataPembayaranSppDetailPresenter {
    void onLoadDataListPertemuan(String id_bayar_spp, String id_wali_murid);

    void onBayar(String id_wali_murid, String id_admin, String total_pertemuan, String total_harga_spp);
}

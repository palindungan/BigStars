package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter;

public interface IDataPembayaranSppDetailPresenter {
    void onLoadDataListPertemuan(String id_bayar_fee, String id_pengajar);

    void onBayar(String id_pengajar, String id_admin, String total_pertemuan, String total_harga_fee);
}

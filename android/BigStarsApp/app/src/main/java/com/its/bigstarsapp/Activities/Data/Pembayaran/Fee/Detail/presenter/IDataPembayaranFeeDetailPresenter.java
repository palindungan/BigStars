package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter;

public interface IDataPembayaranFeeDetailPresenter {
    void onLoadDataListPertemuan(String id_bayar_fee, String id_pengajar);

    void onBayar(String id_pengajar, String id_admin, String total_pertemuan, String total_harga_fee);
}

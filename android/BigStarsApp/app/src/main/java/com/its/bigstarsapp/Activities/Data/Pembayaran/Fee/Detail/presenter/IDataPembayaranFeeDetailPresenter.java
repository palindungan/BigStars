package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter;

public interface IDataPembayaranFeeDetailPresenter {
    void onLoadDataDetail(String id_pertemuan, String id_pengajar);

    void onLoadDataListPertemuan(String id_pengajar);
}

package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.presenter;

public interface IDataKelasPertemuanAddPresenter {
    void onSubmit(String id_pengajar,
                  String id_mata_pelajaran,
                  String hari,
                  String jam_mulai,
                  String jam_berakhir,
                  String harga_fee,
                  String harga_spp);

    void onLoadDataListMataPelajaran();
}

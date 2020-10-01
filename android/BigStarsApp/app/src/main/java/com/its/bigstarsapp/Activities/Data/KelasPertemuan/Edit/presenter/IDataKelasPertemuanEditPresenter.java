package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter;

public interface IDataKelasPertemuanEditPresenter {
    void onUpdate(String id_kelas_pertemuan,
                  String id_pengajar,
                  String id_mata_pelajaran,
                  String hari,
                  String jam_mulai,
                  String jam_berakhir,
                  String harga_fee,
                  String harga_spp);

    void onSharingKelasPertemuan(String id_kelas_pertemuan,
                                 String id_sharing,
                                 String nama_sharing);

    void onLoadDataListMataPelajaran();

    void onLoadDataListPengajar();
}

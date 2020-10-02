package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.presenter;

public interface IDataKelasPertemuanEditPresenter {
    void onSubmitkelasPertemuanDetail(String id_kelas_pertemuan,
                                      String id_murid);

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

    void onDeleteSharingKelasPertemuan(String id_kelas_pertemuan);

    void onLoadDataListMataPelajaran();

    void onLoadDataListPengajar();

    void onLoadDataListMuridSemua();

    void onLoadDataListMurid(String id_kelas_pertemuan);

    void onDeleteMurid(String id_kelas_pertemuan_detail, String id_kelas_pertemuan);
}

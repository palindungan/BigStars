package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.view;

import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.Models.Pengajar;

import java.util.ArrayList;

public interface IDataKelasPertemuanEditView {
    void onSetupListViewMataPelajaran(ArrayList<MataPelajaran> dataModelArrayList);

    void onSetupListViewPengajar(ArrayList<Pengajar> dataModelArrayList);

    void backPressed();

    void onSetupListViewMurid(ArrayList<Murid> dataModelArrayList);

    void showDialogDeleteMurid(String id_kelas_pertemuan,String id_murid, String nama);
}

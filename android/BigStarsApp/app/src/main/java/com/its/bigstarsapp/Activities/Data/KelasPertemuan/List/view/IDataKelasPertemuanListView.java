package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view;

import com.its.bigstarsapp.Models.KelasPertemuan;

import java.util.ArrayList;

public interface IDataKelasPertemuanListView {
    void onSetupListView(ArrayList<KelasPertemuan> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}

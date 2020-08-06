package com.its.bigstarsapp.Activities.Data.MataPelajaran.List.view;

import com.its.bigstarsapp.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataMataPelajaranListView {
    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}

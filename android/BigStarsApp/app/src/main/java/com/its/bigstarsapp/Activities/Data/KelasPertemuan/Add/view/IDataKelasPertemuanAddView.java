package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.view;

import com.its.bigstarsapp.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataKelasPertemuanAddView {
    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void backPressed();
}

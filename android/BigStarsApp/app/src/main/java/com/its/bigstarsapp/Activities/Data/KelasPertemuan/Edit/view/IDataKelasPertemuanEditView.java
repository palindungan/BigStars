package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.view;

import com.its.bigstarsapp.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataKelasPertemuanEditView {
    void onSetupListViewMataPelajaran(ArrayList<MataPelajaran> dataModelArrayList);

    void backPressed();
}

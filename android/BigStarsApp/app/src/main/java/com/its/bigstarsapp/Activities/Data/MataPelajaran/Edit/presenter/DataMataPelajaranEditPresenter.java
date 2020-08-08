package com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;

public class DataMataPelajaranEditPresenter implements IDataMataPelajaranEditPresenter {

    Context context;
    IDataMataPelajaranEditView dataMataPelajaranEditView;

    public DataMataPelajaranEditPresenter(Context context, IDataMataPelajaranEditView dataMataPelajaranEditView) {
        this.context = context;
        this.dataMataPelajaranEditView = dataMataPelajaranEditView;
    }

    @Override
    public void onUpdate(String id_mata_pelajaran, String nama) {

    }
}

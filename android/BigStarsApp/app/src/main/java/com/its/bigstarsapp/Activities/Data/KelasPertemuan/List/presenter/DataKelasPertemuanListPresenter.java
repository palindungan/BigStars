package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view.IDataKelasPertemuanListView;

public class DataKelasPertemuanListPresenter implements IDataKelasPertemuanListPresenter {

    Context context;
    IDataKelasPertemuanListView dataKelasPertemuanListView;

    public DataKelasPertemuanListPresenter(Context context, IDataKelasPertemuanListView dataKelasPertemuanListView) {
        this.context = context;
        this.dataKelasPertemuanListView = dataKelasPertemuanListView;
    }

    @Override
    public void onLoadDataList(String id_pengajar) {

    }

    @Override
    public void onDelete(String id_kelas_pertemuan) {

    }
}

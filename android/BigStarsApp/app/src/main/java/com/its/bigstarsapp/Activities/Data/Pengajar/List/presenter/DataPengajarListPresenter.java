package com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pengajar.List.view.IDataPengajarListView;

public class DataPengajarListPresenter implements IDataPengajarListPresenter {

    Context context;
    IDataPengajarListView dataPengajarListView;

    public DataPengajarListPresenter(Context context, IDataPengajarListView dataPengajarListView) {
        this.context = context;
        this.dataPengajarListView = dataPengajarListView;
    }

    @Override
    public void onLoadDataList() {

    }

    @Override
    public void onDelete(String id_pengajar) {

    }
}

package com.its.bigstarsapp.Activities.Data.Pengajar.Add.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pengajar.Add.view.IDataPengajarAddView;

public class DataPengajarAddPresenter implements IDataPengajarAddPresenter {

    Context context;
    IDataPengajarAddView dataPengajarAddView;

    public DataPengajarAddPresenter(Context context, IDataPengajarAddView dataPengajarAddView) {
        this.context = context;
        this.dataPengajarAddView = dataPengajarAddView;
    }

    @Override
    public void onSubmit(String nama, String username, String password, String alamat, String no_hp, String foto) {

    }
}

package com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.view.IDataKelasPertemuanAddView;

public class DataKelasPertemuanAddPresenter implements IDataKelasPertemuanAddPresenter {
    Context context;
    IDataKelasPertemuanAddView dataKelasPertemuanAddView;

    public DataKelasPertemuanAddPresenter(Context context, IDataKelasPertemuanAddView dataKelasPertemuanAddView) {
        this.context = context;
        this.dataKelasPertemuanAddView = dataKelasPertemuanAddView;
    }
}

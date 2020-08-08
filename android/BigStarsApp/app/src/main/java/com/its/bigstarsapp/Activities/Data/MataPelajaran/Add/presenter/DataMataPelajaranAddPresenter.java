package com.its.bigstarsapp.Activities.Data.MataPelajaran.Add.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.Add.view.IDataMataPelajaranAddView;

public class DataMataPelajaranAddPresenter implements IDataMataPelajaranAddPresenter {

    Context context;
    IDataMataPelajaranAddView dataMataPelajaranAddView;

    public DataMataPelajaranAddPresenter(Context context, IDataMataPelajaranAddView dataMataPelajaranAddView) {
        this.context = context;
        this.dataMataPelajaranAddView = dataMataPelajaranAddView;
    }

    @Override
    public void onSubmit(String nama) {

    }
}

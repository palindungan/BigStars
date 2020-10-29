package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;

public class DataPertemuanEdit2Presenter implements IDataPertemuanEdit2Presenter {
    Context context;
    IDataPertemuanEdit2View dataPertemuanEdit2View;

    public DataPertemuanEdit2Presenter(Context context, IDataPertemuanEdit2View dataPertemuanEdit2View) {
        this.context = context;
        this.dataPertemuanEdit2View = dataPertemuanEdit2View;
    }
}

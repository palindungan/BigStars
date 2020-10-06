package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.view.IDataPertemuanEditView;

public class DataPertemuanEditPresenter implements IDataPertemuanEditPresenter {
    Context context;
    IDataPertemuanEditView dataPertemuanEditView;

    public DataPertemuanEditPresenter(Context context, IDataPertemuanEditView dataPertemuanEditView) {
        this.context = context;
        this.dataPertemuanEditView = dataPertemuanEditView;
    }
}

package com.its.bigstarsapp.Activities.Data.Murid.Edit.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Murid.Edit.view.IDataMuridEditView;

public class DataMuridEditPresenter implements IDataMuridEditPresenter {

    Context context;
    IDataMuridEditView dataMuridEditView;

    public DataMuridEditPresenter(Context context, IDataMuridEditView dataMuridEditView) {
        this.context = context;
        this.dataMuridEditView = dataMuridEditView;
    }

    @Override
    public void onUpdate(String id_murid, String id_wali_murid, String nama, String foto) {

    }

    @Override
    public void onLoadDataListWaliMurid() {

    }
}

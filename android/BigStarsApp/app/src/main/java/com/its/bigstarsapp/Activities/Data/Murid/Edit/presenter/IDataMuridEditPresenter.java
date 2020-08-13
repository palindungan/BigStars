package com.its.bigstarsapp.Activities.Data.Murid.Edit.presenter;

public interface IDataMuridEditPresenter {
    void onUpdate(String id_murid, String id_wali_murid, String nama, String foto);

    void onLoadDataListWaliMurid();
}

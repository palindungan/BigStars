package com.its.bigstarsapp.Activities.Data.Murid.Add.presenter;

public interface IDataMuridAddPresenter {
    void onSubmit(String id_wali_murid, String nama, String foto);

    void onLoadDataListWaliMurid();
}

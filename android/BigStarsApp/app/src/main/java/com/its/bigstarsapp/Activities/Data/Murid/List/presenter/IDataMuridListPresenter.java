package com.its.bigstarsapp.Activities.Data.Murid.List.presenter;

public interface IDataMuridListPresenter {
    void onLoadDataList();

    void onDelete(String id_murid);

    void onLoadDataListMuridBy(String id_wali_murid);
}

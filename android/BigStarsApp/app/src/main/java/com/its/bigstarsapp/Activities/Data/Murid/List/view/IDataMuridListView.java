package com.its.bigstarsapp.Activities.Data.Murid.List.view;

import com.its.bigstarsapp.Models.Murid;

import java.util.ArrayList;

public interface IDataMuridListView {
    void onSetupListView(ArrayList<Murid> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}

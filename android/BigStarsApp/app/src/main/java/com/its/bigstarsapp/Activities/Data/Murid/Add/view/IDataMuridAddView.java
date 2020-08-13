package com.its.bigstarsapp.Activities.Data.Murid.Add.view;

import com.its.bigstarsapp.Models.WaliMurid;

import java.util.ArrayList;

public interface IDataMuridAddView {
    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void backPressed();
}

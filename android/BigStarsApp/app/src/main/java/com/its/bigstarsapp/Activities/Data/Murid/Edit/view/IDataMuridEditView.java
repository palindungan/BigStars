package com.its.bigstarsapp.Activities.Data.Murid.Edit.view;

import com.its.bigstarsapp.Models.WaliMurid;

import java.util.ArrayList;

public interface IDataMuridEditView {
    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void backPressed();
}

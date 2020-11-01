package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.view;

import com.its.bigstarsapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IDataPembayaranFeeDetailView {
    void onSetData();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList);

    void backPressed();
}

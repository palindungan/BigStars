package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view;

import com.its.bigstarsapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IDataPembayaranSppDetailView {
    void onSetData(String setNamaWaliMurid, int totalPertemuan, int totalSpp);

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList);

    void backPressed();
}

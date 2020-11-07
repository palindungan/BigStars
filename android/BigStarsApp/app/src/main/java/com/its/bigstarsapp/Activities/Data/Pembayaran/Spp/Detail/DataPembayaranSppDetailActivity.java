package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranSppDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_detail);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetData(String setNamaWaliMurid, String totalPertemuan, String totalSpp) {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {

    }

    @Override
    public void backPressed() {

    }
}
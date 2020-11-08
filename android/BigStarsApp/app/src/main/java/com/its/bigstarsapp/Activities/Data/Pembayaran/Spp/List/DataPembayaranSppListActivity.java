package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.view.IDataPembayaranSppListView;
import com.its.bigstarsapp.Models.BayarSpp;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPembayaranSppListActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_list);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<BayarSpp> dataModelArrayList) {

    }
}
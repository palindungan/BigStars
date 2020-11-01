package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
import com.its.bigstarsapp.R;

public class DataPembayaranSppDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_detail);
    }

    @Override
    public void onClick(View view) {

    }
}
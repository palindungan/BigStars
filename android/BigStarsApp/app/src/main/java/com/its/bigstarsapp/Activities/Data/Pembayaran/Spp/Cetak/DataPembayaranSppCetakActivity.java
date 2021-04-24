package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Cetak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Cetak.view.IDataPembayaranSppCetakView;
import com.its.bigstarsapp.R;

public class DataPembayaranSppCetakActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppCetakView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_cetak);
    }

    @Override
    public void onClick(View view) {

    }
}
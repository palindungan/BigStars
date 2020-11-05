package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.view.IDataPembayaranFeeListView;
import com.its.bigstarsapp.R;

public class DataPembayaranFeeListActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranFeeListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_fee_list);
    }

    @Override
    public void onClick(View view) {

    }
}
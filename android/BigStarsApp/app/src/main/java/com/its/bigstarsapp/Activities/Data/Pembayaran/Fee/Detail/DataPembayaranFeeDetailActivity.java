package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.IDataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.view.IDataPembayaranFeeDetailView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class DataPembayaranFeeDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranFeeDetailView {

    IDataPembayaranFeeDetailPresenter dataPembayaranFeeDetailPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_fee_detail);
    }

    @Override
    public void onClick(View view) {

    }
}
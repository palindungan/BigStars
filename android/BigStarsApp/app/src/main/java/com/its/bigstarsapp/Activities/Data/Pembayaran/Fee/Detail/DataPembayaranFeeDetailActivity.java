package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.DataPembayaranFeeDetailPresenter;
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

    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_fee_detail);

        dataPembayaranFeeDetailPresenter = new DataPembayaranFeeDetailPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);

        globalProcess.initActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {

    }
}
package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.view.IDataPembayaranFeeListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarFee;

import java.util.ArrayList;

public class DataPembayaranFeeListPresenter implements IDataPembayaranFeeListPresenter {

    Context context;
    IDataPembayaranFeeListView dataPembayaranFeeListView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<BayarFee> dataModelArrayList;

    public DataPembayaranFeeListPresenter(Context context, IDataPembayaranFeeListView dataPembayaranFeeListView) {
        this.context = context;
        this.dataPembayaranFeeListView = dataPembayaranFeeListView;
    }

    @Override
    public void onLoadDataList() {

    }
}

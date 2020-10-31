package com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.view.IDataPembayaranFeeDetailView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

public class DataPembayaranFeeDetailPresenter implements IDataPembayaranFeeDetailPresenter {
    Context context;
    IDataPembayaranFeeDetailView dataPembayaranFeeDetailView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataPembayaranFeeDetailPresenter(Context context, IDataPembayaranFeeDetailView dataPembayaranFeeDetailView) {
        this.context = context;
        this.dataPembayaranFeeDetailView = dataPembayaranFeeDetailView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }
}

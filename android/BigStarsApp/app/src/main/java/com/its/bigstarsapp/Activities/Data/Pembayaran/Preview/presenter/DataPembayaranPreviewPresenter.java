package com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Preview.view.IDataPembayaranPreviewView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

public class DataPembayaranPreviewPresenter implements IDataPembayaranPreviewPresenter {
    Context context;
    IDataPembayaranPreviewView dataPembayaranPreviewView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataPembayaranPreviewPresenter(Context context, IDataPembayaranPreviewView dataPembayaranPreviewView) {
        this.context = context;
        this.dataPembayaranPreviewView = dataPembayaranPreviewView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }
}

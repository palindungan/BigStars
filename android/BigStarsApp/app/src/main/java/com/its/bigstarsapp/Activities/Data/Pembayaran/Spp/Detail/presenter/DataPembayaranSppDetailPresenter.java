package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;

import java.util.ArrayList;

public class DataPembayaranSppDetailPresenter implements IDataPembayaranSppDetailPresenter {

    Context context;
    IDataPembayaranSppDetailView dataPembayaranSppDetailView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    ArrayList<Pertemuan> dataModelArrayList;

    public DataPembayaranSppDetailPresenter(Context context, IDataPembayaranSppDetailView dataPembayaranSppDetailView) {
        this.context = context;
        this.dataPembayaranSppDetailView = dataPembayaranSppDetailView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onLoadDataListPertemuan(String id_bayar_spp, String id_wali_murid) {

    }

    @Override
    public void onBayar(String id_wali_murid, String id_admin, String total_pertemuan, String total_spp) {

    }
}

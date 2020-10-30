package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit2.view.IDataPertemuanEdit2View;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

public class DataPertemuanEdit2Presenter implements IDataPertemuanEdit2Presenter {
    Context context;
    IDataPertemuanEdit2View dataPertemuanEdit2View;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataPertemuanEdit2Presenter(Context context, IDataPertemuanEdit2View dataPertemuanEdit2View) {
        this.context = context;
        this.dataPertemuanEdit2View = dataPertemuanEdit2View;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onFinish(String id_pertemuan, String deskripsi, String lokasi_berakhir_la, String lokasi_berakhir_lo) {
        globalProcess.onSuccessMessage("berhasil");
    }
}

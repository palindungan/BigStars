package com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Data.Pertemuan.Edit.view.IDataPertemuanEditView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;

public class DataPertemuanEditPresenter implements IDataPertemuanEditPresenter {
    Context context;
    IDataPertemuanEditView dataPertemuanEditView;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    public DataPertemuanEditPresenter(Context context, IDataPertemuanEditView dataPertemuanEditView) {
        this.context = context;
        this.dataPertemuanEditView = dataPertemuanEditView;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onBatalPertemuan(String id_pertemuan) {
        globalProcess.onSuccessMessage(id_pertemuan);
    }
}

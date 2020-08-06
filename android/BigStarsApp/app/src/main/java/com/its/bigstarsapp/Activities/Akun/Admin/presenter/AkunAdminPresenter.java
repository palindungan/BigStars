package com.its.bigstarsapp.Activities.Akun.Admin.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities.Akun.Admin.view.IAkunAdminView;

public class AkunAdminPresenter implements IAkunAdminPresenter {

    Context context;
    IAkunAdminView akunAdminView;

    public AkunAdminPresenter(Context context, IAkunAdminView akunAdminView) {
        this.context = context;
        this.akunAdminView = akunAdminView;
    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {

    }

    @Override
    public void onLoadData(String id_admin) {

    }
}

package com.its.bigstarsapp.Activities.Akun.Admin.presenter;

public interface IAkunAdminPresenter {
    void onLoadData(String id_admin);

    void onUpdate(String id_admin, String nama, String username, String password, String foto);
}

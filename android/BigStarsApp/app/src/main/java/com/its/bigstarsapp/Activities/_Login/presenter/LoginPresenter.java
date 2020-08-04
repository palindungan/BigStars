package com.its.bigstarsapp.Activities._Login.presenter;

import android.content.Context;

import com.its.bigstarsapp.Activities._Login.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    Context context;
    ILoginView loginView;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String password, String hak_akses) {

    }
}

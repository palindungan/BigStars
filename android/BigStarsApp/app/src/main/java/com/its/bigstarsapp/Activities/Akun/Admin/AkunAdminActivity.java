package com.its.bigstarsapp.Activities.Akun.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Akun.Admin.view.IAkunAdminView;
import com.its.bigstarsapp.R;

public class AkunAdminActivity extends AppCompatActivity implements View.OnClickListener, IAkunAdminView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);
    }

    @Override
    public void onClick(View view) {

    }
}
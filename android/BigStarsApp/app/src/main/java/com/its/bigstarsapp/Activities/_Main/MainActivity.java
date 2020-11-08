package com.its.bigstarsapp.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.bigstarsapp.Activities._Login.LoginActivity;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Button btnLoginAdmin, btnLoginPengajar, btnLoginWaliMurid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        btnLoginAdmin = findViewById(R.id.btn_login_admin);
        btnLoginPengajar = findViewById(R.id.btn_login_pengajar);
        btnLoginWaliMurid = findViewById(R.id.btn_login_wali_murid);

        sessionManager.checkLogin();

        btnLoginAdmin.setOnClickListener(this);
        btnLoginPengajar.setOnClickListener(this);
        btnLoginWaliMurid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String hak_akses = "";
        Intent intent;

        if (view.getId() == R.id.btn_login_admin) {
            hak_akses = "admin";
        } else if (view.getId() == R.id.btn_login_pengajar) {
            hak_akses = "pengajar";
        } else if (view.getId() == R.id.btn_login_wali_murid) {
            hak_akses = "wali_murid";
        }

        if (!hak_akses.equals("")) {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra(LoginActivity.EXTRA_HAK_AKSES, hak_akses);
            startActivity(intent);
        }
    }
}
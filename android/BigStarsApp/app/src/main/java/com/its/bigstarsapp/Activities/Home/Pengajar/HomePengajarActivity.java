package com.its.bigstarsapp.Activities.Home.Pengajar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

public class HomePengajarActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    GlobalProcess globalProcess;

    CardView linkPengajarKelasPertemuan, linkPengajarKelasPertemuanAktif, linkPengajarRiwayatAbsen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengajar);

        sessionManager = new SessionManager(this);
        globalProcess = new GlobalProcess(this);

        linkPengajarKelasPertemuan = findViewById(R.id.link_pengajar_kelas_pertemuan);
        linkPengajarKelasPertemuanAktif = findViewById(R.id.link_pengajar_kelas_pertemuan_aktif);
        linkPengajarRiwayatAbsen = findViewById(R.id.link_pengajar_riwayat_absen);

        linkPengajarKelasPertemuan.setOnClickListener(this);
        linkPengajarKelasPertemuanAktif.setOnClickListener(this);
        linkPengajarRiwayatAbsen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.link_pengajar_kelas_pertemuan) {
//            sessionManager.setStatusActivity("home->view->editPengajar");
//            intent = new Intent(getApplicationContext(), DataPengajarListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_pengajar_kelas_pertemuan_aktif) {
            //
        } else if (view.getId() == R.id.link_pengajar_riwayat_absen) {
            //
        }
    }
}
package com.its.bigstarsapp.Activities.Home.Pengajar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.DataKelasPertemuanListActivity;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.DataPertemuanListActivity;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.HashMap;

public class HomePengajarActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    GlobalProcess globalProcess;

    CardView linkPengajarKelasPertemuan, linkPengajarKelasPertemuanAktif, linkPengajarRiwayatAbsen;

    String id_pengajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengajar);

        sessionManager = new SessionManager(this);
        globalProcess = new GlobalProcess(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_pengajar = user.get(sessionManager.ID_USER);

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
            sessionManager.setStatusActivity("homePengajar->view->dataKelasPertemuanEdit");
            intent = new Intent(getApplicationContext(), DataKelasPertemuanListActivity.class);
            intent.putExtra(DataKelasPertemuanListActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        } else if (view.getId() == R.id.link_pengajar_kelas_pertemuan_aktif) {
            sessionManager.setStatusActivity("homePengajar->view->dataPertemuanAktif");
            intent = new Intent(getApplicationContext(), DataPertemuanListActivity.class);
            intent.putExtra(DataPertemuanListActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        } else if (view.getId() == R.id.link_pengajar_riwayat_absen) {
            sessionManager.setStatusActivity("homePengajar->view->dataPertemuanHistory");
            intent = new Intent(getApplicationContext(), DataPertemuanListActivity.class);
            intent.putExtra(DataPertemuanListActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        }
    }
}
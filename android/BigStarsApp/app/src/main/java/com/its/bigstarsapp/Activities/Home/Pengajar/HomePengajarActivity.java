package com.its.bigstarsapp.Activities.Home.Pengajar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.DataKelasPertemuanListActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.DataPembayaranFeeDetailActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.List.DataPembayaranFeeListActivity;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.DataPertemuanListActivity;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.HashMap;

public class HomePengajarActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    GlobalProcess globalProcess;

    CardView linkPengajarKelasPertemuan, linkPengajarKelasPertemuanAktif, linkPengajarRiwayatAbsen;
    CardView cvDetailTagihanFee, cvRiwayatPembayaranFee;

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

        cvDetailTagihanFee = findViewById(R.id.cv_detail_tagihan_fee);
        cvRiwayatPembayaranFee = findViewById(R.id.cv_riwayat_pembayaran_fee);

        linkPengajarKelasPertemuan.setOnClickListener(this);
        linkPengajarKelasPertemuanAktif.setOnClickListener(this);
        linkPengajarRiwayatAbsen.setOnClickListener(this);

        cvDetailTagihanFee.setOnClickListener(this);
        cvRiwayatPembayaranFee.setOnClickListener(this);
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
        } else if (view.getId() == R.id.cv_detail_tagihan_fee) {
            sessionManager.setStatusActivity("homePengajar->view->detailPembayaranFee");
            intent = new Intent(getApplicationContext(), DataPembayaranFeeDetailActivity.class);
            intent.putExtra(DataPembayaranFeeDetailActivity.EXTRA_ID_BAYAR_FEE, "kosong");
            intent.putExtra(DataPembayaranFeeDetailActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        } else if (view.getId() == R.id.cv_riwayat_pembayaran_fee) {
            sessionManager.setStatusActivity("homePengajar->view->ListPembayaranFee");
            intent = new Intent(getApplicationContext(), DataPembayaranFeeListActivity.class);
            intent.putExtra(DataPembayaranFeeListActivity.EXTRA_ID_BAYAR_FEE, "kosong");
            intent.putExtra(DataPembayaranFeeListActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_keluar) {
            globalProcess.dialogLogout(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
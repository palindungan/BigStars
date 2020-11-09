package com.its.bigstarsapp.Activities.Home.WaliMurid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Murid.List.DataMuridListActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.DataPembayaranSppDetailActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.List.DataPembayaranSppListActivity;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.HashMap;

public class HomeWaliMuridActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    GlobalProcess globalProcess;

    CardView cvLihatKelasPertemuan, cvDetailTagihanSpp, cvRiwayatPembayaranSpp;

    String id_wali_murid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wali_murid);

        sessionManager = new SessionManager(this);
        globalProcess = new GlobalProcess(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_wali_murid = user.get(sessionManager.ID_USER);

        cvLihatKelasPertemuan = findViewById(R.id.cv_lihat_kelas_pertemuan);
        cvDetailTagihanSpp = findViewById(R.id.cv_detail_tagihan_spp);
        cvRiwayatPembayaranSpp = findViewById(R.id.cv_riwayat_pembayaran_spp);

        cvLihatKelasPertemuan.setOnClickListener(this);
        cvDetailTagihanSpp.setOnClickListener(this);
        cvRiwayatPembayaranSpp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.cv_lihat_kelas_pertemuan) {
            sessionManager.setStatusActivity("homeWaliMurid->view->dataMuridList");
            intent = new Intent(getApplicationContext(), DataMuridListActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.cv_detail_tagihan_spp) {
            intent = new Intent(getApplicationContext(), DataPembayaranSppDetailActivity.class);
            intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_BAYAR_SPP, "kosong");
            intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_WALI_MURID, id_wali_murid);
            startActivity(intent);
        } else if (view.getId() == R.id.cv_riwayat_pembayaran_spp) {
            intent = new Intent(getApplicationContext(), DataPembayaranSppListActivity.class);
            intent.putExtra(DataPembayaranSppListActivity.EXTRA_ID_BAYAR_SPP, "kosong");
            intent.putExtra(DataPembayaranSppListActivity.EXTRA_ID_WALI_MURID, id_wali_murid);
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
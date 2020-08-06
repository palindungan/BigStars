package com.its.bigstarsapp.Activities.Home.Admin;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.its.bigstarsapp.Activities.Akun.Admin.AkunAdminActivity;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.R;

import java.util.Objects;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    GlobalProcess globalProcess;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    CardView linkAdminPengajar, linkAdminMurid, linkAdminWaliMurid, linkAdminMataPelajaran, linkAdminKelas, linkAdminKelasAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        sessionManager = new SessionManager(this);
        globalProcess = new GlobalProcess(this);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout_admin);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        NavigationView navigationView = findViewById(R.id.navigation_view_admin);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            // Intent intent = new Intent();
            switch (id) {
                case R.id.monitoring:
                    // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                    // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_monitoring");
                    break;
                case R.id.bayar_spp:
                    // intent = new Intent(getApplicationContext(), AdminWaliMuridTampilActivity.class);
                    // intent.putExtra(AdminWaliMuridTampilActivity.EXTRA_STATUS_ACTIVITY, "to_transaksi_spp");
                    break;
                case R.id.riwayat_bayar_spp:
                    // intent = new Intent(getApplicationContext(), AdminWaliMuridTampilActivity.class);
                    // intent.putExtra(AdminWaliMuridTampilActivity.EXTRA_STATUS_ACTIVITY, "to_riwayat_spp");
                    break;
                case R.id.gaji_pengajar:
                    // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                    // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_transaksi_gaji");
                    break;
                case R.id.riwayat_penggajian:
                    // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                    // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_riwayat_gaji");
                    break;
                default:
                    return true;
            }
            // startActivity(intent);
            return true;
        });

        linkAdminPengajar = findViewById(R.id.link_admin_pengajar);
        linkAdminMurid = findViewById(R.id.link_admin_murid);
        linkAdminWaliMurid = findViewById(R.id.link_admin_wali_murid);
        linkAdminMataPelajaran = findViewById(R.id.link_admin_mata_pelajaran);
        linkAdminKelas = findViewById(R.id.link_admin_kelas);
        linkAdminKelasAktif = findViewById(R.id.link_admin_kelas_aktif);

        linkAdminPengajar.setOnClickListener(this);
        linkAdminMurid.setOnClickListener(this);
        linkAdminWaliMurid.setOnClickListener(this);
        linkAdminMataPelajaran.setOnClickListener(this);
        linkAdminKelas.setOnClickListener(this);
        linkAdminKelasAktif.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.link_admin_pengajar) {
//            sessionManager.setStatusActivity("home->view->editPengajar");
//            intent = new Intent(getApplicationContext(), DataPengajarListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_admin_murid) {
//            sessionManager.setStatusActivity("home->view->editMurid");
//            intent = new Intent(getApplicationContext(), DataMuridListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_admin_wali_murid) {
//            sessionManager.setStatusActivity("home->view->editWaliMurid");
//            intent = new Intent(getApplicationContext(), DataWaliMuridListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_admin_mata_pelajaran) {
//            sessionManager.setStatusActivity("home->view->editMataPelajaran");
//            intent = new Intent(getApplicationContext(), DataMataPelajaranListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_admin_kelas) {
//            globalProcess.onSuccessMessage("Pilih Pengajar");
//            sessionManager.setStatusActivity("home->view->listKelasPertemuan");
//            intent = new Intent(getApplicationContext(), DataPengajarListActivity.class);
//            startActivity(intent);
        } else if (view.getId() == R.id.link_admin_kelas_aktif) {
//            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilAktifActivity.class);
//            String id_pengajar = "Semua";
//            intent.putExtra(PengajarKelasTampilAktifActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.menu_riwayat) {
//            String id_pengajar = "Semua";
//            intent = new Intent(getApplicationContext(), PengajarRiwayatAbsenActivity.class);
//            intent.putExtra(PengajarRiwayatAbsenActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
            return true;
        } else if (id == R.id.menu_akun_saya) {
            intent = new Intent(getApplicationContext(), AkunAdminActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_keluar) {
            globalProcess.dialogLogout(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
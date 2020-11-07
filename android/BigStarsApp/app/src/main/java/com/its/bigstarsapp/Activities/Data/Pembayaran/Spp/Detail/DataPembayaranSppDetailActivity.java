package com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.presenter.DataPembayaranFeeDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter.DataPembayaranSppDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.presenter.IDataPembayaranSppDetailPresenter;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.view.IDataPembayaranSppDetailView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPembayaranSppDetailActivity extends AppCompatActivity implements View.OnClickListener, IDataPembayaranSppDetailView {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";

    IDataPembayaranSppDetailPresenter dataPembayaranSppDetailPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText edtNamaWaliMurid, edtTotalPertemuan, edtTotalSpp;
    Button btnBayar;

    String id_wali_murid, id_bayar_spp, id_user;
    String hak_akses;
    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran_spp_detail);

        dataPembayaranSppDetailPresenter = new DataPembayaranSppDetailPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtTotalPertemuan = findViewById(R.id.edt_total_pertemuan);
        edtTotalSpp = findViewById(R.id.edt_total_spp);

        btnBayar = findViewById(R.id.btn_bayar);

        globalProcess.initActionBar(toolbar);

        id_bayar_spp = getIntent().getStringExtra(EXTRA_ID_BAYAR_SPP);
        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_user = user.get(sessionManager.ID_USER);
        hak_akses = user.get(sessionManager.HAK_AKSES);
        statusActivity = sessionManager.getStatusActivity();

        onLoadData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            onLoadData();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        btnBayar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_bayar) {
            showDialogBayar();
        }
    }

    @Override
    public void onSetData(String setNamaWaliMurid, int totalPertemuan, int totalSpp) {
        edtNamaWaliMurid.setText(setNamaWaliMurid);
        edtTotalPertemuan.setText(String.valueOf(totalPertemuan));
        edtTotalSpp.setText(String.valueOf(totalSpp));

        if (totalPertemuan > 0 && hak_akses.equals("admin")) {
            if (!statusActivity.equals("home->view->ListPembayaranSpp")) {
                btnBayar.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {

    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        onLoadData();
    }

    private void onLoadData() {
        dataPembayaranSppDetailPresenter.onLoadDataListPertemuan(
                "" + id_bayar_spp,
                "" + id_wali_murid);
    }

    private void showDialogBayar() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Lakukan Pembayaran SPP ?");
        alertDialogBuilder
                .setMessage("Pilih Ya untuk melakukan pembayaran SPP.")
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {
                    String inputTotalPertemuan = edtTotalPertemuan.getText().toString().trim();
                    String inputTotalSpp = edtTotalSpp.getText().toString().trim();
                    boolean isEmpty = false;
                    if (TextUtils.isEmpty(inputTotalPertemuan)) {
                        isEmpty = true;
                        edtTotalPertemuan.setError("Data Kosong");
                    } else if (TextUtils.isEmpty(inputTotalSpp)) {
                        isEmpty = true;
                        edtTotalSpp.setError("Data Kosong");
                    }
                    try {
                        if (!isEmpty) {
                            globalProcess.onSuccessMessage("berhasil");
                            dataPembayaranSppDetailPresenter.onBayar(
                                    "" + id_wali_murid,
                                    "" + id_user,
                                    "" + inputTotalPertemuan,
                                    "" + inputTotalSpp);
                        }
                    } catch (Exception e) {
                        globalProcess.onErrorMessage("Gagal Melakukan Pembayaran " + e.toString());
                    }
                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
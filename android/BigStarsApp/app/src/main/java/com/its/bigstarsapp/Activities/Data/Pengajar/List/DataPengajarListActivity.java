package com.its.bigstarsapp.Activities.Data.Pengajar.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.DataKelasPertemuanListActivity;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Fee.Detail.DataPembayaranFeeDetailActivity;
import com.its.bigstarsapp.Activities.Data.Pengajar.Add.DataPengajarAddActivity;
import com.its.bigstarsapp.Activities.Data.Pengajar.Edit.DataPengajarEditActivity;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter.DataPengajarListPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter.IDataPengajarListPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.DataPertemuanListActivity;
import com.its.bigstarsapp.Adapters.AdapterDataPengajarList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pengajar;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPengajarListActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarListView {

    IDataPengajarListPresenter dataPengajarListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_list);

        dataPengajarListPresenter = new DataPengajarListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        dataPengajarListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataPengajarListPresenter.onLoadDataList();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("home->view->editPengajar")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), DataPengajarAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<Pengajar> dataModelArrayList) {
        AdapterDataPengajarList adapterDataPengajarList = new AdapterDataPengajarList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataPengajarList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataPengajarList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editPengajar")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataPengajarList.setOnItemClickListener((view, position) -> {
            Intent intent;
            if (statusActivity.equals("home->view->editPengajar")) {
                intent = new Intent(getApplicationContext(), DataPengajarEditActivity.class);
                intent.putExtra(DataPengajarEditActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                intent.putExtra(DataPengajarEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(DataPengajarEditActivity.EXTRA_USERNAME, dataModelArrayList.get(position).getUsername());
                intent.putExtra(DataPengajarEditActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(DataPengajarEditActivity.EXTRA_NO_HP, dataModelArrayList.get(position).getNo_hp());
                intent.putExtra(DataPengajarEditActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
                startActivity(intent);
            } else if (statusActivity.equals("home->view->listKelasPertemuan")) {
                sessionManager.setStatusActivity("listPengajar->view->editKelasPertemuan");
                intent = new Intent(getApplicationContext(), DataKelasPertemuanListActivity.class);
                intent.putExtra(DataKelasPertemuanListActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                startActivity(intent);
            } else if (statusActivity.equals("home->view->listPertemuanAktif") || statusActivity.equals("home->view->listPertemuanSemuaRiwayat")) {
                intent = new Intent(getApplicationContext(), DataPertemuanListActivity.class);
                intent.putExtra(DataPertemuanListActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                startActivity(intent);
            } else if (statusActivity.equals("home->view->detailPembayaranFee")) {
                intent = new Intent(getApplicationContext(), DataPembayaranFeeDetailActivity.class);
                intent.putExtra(DataPembayaranFeeDetailActivity.EXTRA_ID_BAYAR_FEE, "kosong");
                intent.putExtra(DataPembayaranFeeDetailActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showDialogDelete(String kode, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(globalMessage.getValidasiHapusData() + nama + " ?");
        alertDialogBuilder
                .setMessage(globalMessage.getPilihYaHapusData())
                .setPositiveButton(globalMessage.getYa(), (dialog, id) -> {

                    try {
                        dataPengajarListPresenter.onDelete(kode);
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorHapusData() + e.toString());
                    }

                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        dataPengajarListPresenter.onLoadDataList();
    }
}
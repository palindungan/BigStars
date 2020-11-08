package com.its.bigstarsapp.Activities.Data.WaliMurid.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.Pembayaran.Spp.Detail.DataPembayaranSppDetailActivity;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Add.DataWaliMuridAddActivity;
import com.its.bigstarsapp.Activities.Data.WaliMurid.Edit.DataWaliMuridEditActivity;
import com.its.bigstarsapp.Activities.Data.WaliMurid.List.presenter.DataWaliMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.List.presenter.IDataWaliMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstarsapp.Adapters.AdapterDataWaliMuridList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.WaliMurid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataWaliMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridListView {

    IDataWaliMuridListPresenter dataWaliMuridListPresenter;

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
        setContentView(R.layout.activity_data_wali_murid_list);

        dataWaliMuridListPresenter = new DataWaliMuridListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        dataWaliMuridListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataWaliMuridListPresenter.onLoadDataList();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("home->view->editWaliMurid")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), DataWaliMuridAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {
        AdapterDataWaliMuridList adapterDataWaliMuridList = new AdapterDataWaliMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataWaliMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataWaliMuridList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editWaliMurid")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataWaliMuridList.setOnItemClickListener((view, position) -> {
            Intent intent;
            if (statusActivity.equals("home->view->editWaliMurid")) {
                intent = new Intent(getApplicationContext(), DataWaliMuridEditActivity.class);
                intent.putExtra(DataWaliMuridEditActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(DataWaliMuridEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(DataWaliMuridEditActivity.EXTRA_USERNAME, dataModelArrayList.get(position).getUsername());
                intent.putExtra(DataWaliMuridEditActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(DataWaliMuridEditActivity.EXTRA_NO_HP, dataModelArrayList.get(position).getNo_hp());
                startActivity(intent);
            }
            if (statusActivity.equals("home->view->detailPembayaranSpp")) {
                intent = new Intent(getApplicationContext(), DataPembayaranSppDetailActivity.class);
                intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_BAYAR_SPP, "kosong");
                intent.putExtra(DataPembayaranSppDetailActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
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
                        dataWaliMuridListPresenter.onDelete(kode);
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
        dataWaliMuridListPresenter.onLoadDataList();
    }
}
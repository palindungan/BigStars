package com.its.bigstarsapp.Activities.Data.Murid.List;

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
import com.its.bigstarsapp.Activities.Data.Murid.Add.DataMuridAddActivity;
import com.its.bigstarsapp.Activities.Data.Murid.Edit.DataMuridEditActivity;
import com.its.bigstarsapp.Activities.Data.Murid.List.presenter.DataMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.Murid.List.presenter.IDataMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.DataPertemuanListActivity;
import com.its.bigstarsapp.Adapters.AdapterDataMuridList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

    IDataMuridListPresenter dataMuridListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    String id_wali_murid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_list);

        dataMuridListPresenter = new DataMuridListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        statusActivity = sessionManager.getStatusActivity();

        HashMap<String, String> user = sessionManager.getDataUser();
        id_wali_murid = user.get(sessionManager.ID_USER);

        onLoad();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            onLoad();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        if (statusActivity.equals("home->view->editMurid")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), DataMuridAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {
        AdapterDataMuridList adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editMurid")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataMuridList.setOnItemClickListener((view, position) -> {
            if (statusActivity.equals("home->view->editMurid")) {
                Intent intent = new Intent(getApplicationContext(), DataMuridEditActivity.class);
                intent.putExtra(DataMuridEditActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
                intent.putExtra(DataMuridEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(DataMuridEditActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(DataMuridEditActivity.EXTRA_NAMA_WALI_MURID, dataModelArrayList.get(position).getNama_wali_murid());
                intent.putExtra(DataMuridEditActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(DataMuridEditActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
                startActivity(intent);
            } else if (statusActivity.equals("homeWaliMurid->view->dataMuridList")) {
                Intent intent = new Intent(getApplicationContext(), DataPertemuanListActivity.class);
                intent.putExtra(DataPertemuanListActivity.EXTRA_ID_PENGAJAR, "kosong");
                intent.putExtra(DataPertemuanListActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
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
                        dataMuridListPresenter.onDelete(kode);
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
        onLoad();
    }

    protected void onLoad() {
        if (statusActivity.equals("homeWaliMurid->view->dataMuridList")) {
            dataMuridListPresenter.onLoadDataListMuridBy("" + id_wali_murid);
        } else {
            dataMuridListPresenter.onLoadDataList();
        }
    }
}
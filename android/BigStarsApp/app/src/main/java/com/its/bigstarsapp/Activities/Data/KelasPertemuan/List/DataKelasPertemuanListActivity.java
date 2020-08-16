package com.its.bigstarsapp.Activities.Data.KelasPertemuan.List;

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
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Add.DataKelasPertemuanAddActivity;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.DataKelasPertemuanEditActivity;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.presenter.DataKelasPertemuanListPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.presenter.IDataKelasPertemuanListPresenter;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view.IDataKelasPertemuanListView;
import com.its.bigstarsapp.Adapters.AdapterDataKelasPertemuanList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.KelasPertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataKelasPertemuanListActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasPertemuanListView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar;

    IDataKelasPertemuanListPresenter dataKelasPertemuanListPresenter;

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
        setContentView(R.layout.activity_data_kelas_pertemuan_list);

        dataKelasPertemuanListPresenter = new DataKelasPertemuanListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        globalProcess.initActionBar(toolbar);

        dataKelasPertemuanListPresenter.onLoadDataList(id_pengajar);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataKelasPertemuanListPresenter.onLoadDataList(id_pengajar);

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.fab) {
            intent = new Intent(getApplicationContext(), DataKelasPertemuanAddActivity.class);
            intent.putExtra(DataKelasPertemuanAddActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        }
    }

    @Override
    public void onSetupListView(ArrayList<KelasPertemuan> dataModelArrayList) {
        AdapterDataKelasPertemuanList adapterDataKelasPertemuanList = new AdapterDataKelasPertemuanList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataKelasPertemuanList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataKelasPertemuanList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataKelasPertemuanList.setOnItemClickListener((view, position) -> {
            Intent intent;
            if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                intent = new Intent(getApplicationContext(), DataKelasPertemuanEditActivity.class);
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_ID_KELAS_PERTEMUAN, dataModelArrayList.get(position).getId_kelas_pertemuan());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_NAMA_PELAJARAN, dataModelArrayList.get(position).getNama_pelajaran());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_HARI, dataModelArrayList.get(position).getHari());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_JAM_BERAKHIR, dataModelArrayList.get(position).getJam_berakhir());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_HARGA_FEE, dataModelArrayList.get(position).getHarga_fee());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_HARGA_SPP, dataModelArrayList.get(position).getHarga_spp());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_ID_SHARING, dataModelArrayList.get(position).getId_sharing());
                intent.putExtra(DataKelasPertemuanEditActivity.EXTRA_NAMA_SHARING, dataModelArrayList.get(position).getNama_sharing());
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
                        dataKelasPertemuanListPresenter.onDelete(kode);
                    } catch (Exception e) {
                        globalProcess.onErrorMessage(globalMessage.getErrorHapusData() + e.toString());
                    }

                })
                .setNegativeButton(globalMessage.getTidak(), (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onRefreshDataList() {
        dataKelasPertemuanListPresenter.onLoadDataList(id_pengajar);
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
        dataKelasPertemuanListPresenter.onLoadDataList(id_pengajar);
    }
}
package com.its.bigstarsapp.Activities.Data.MataPelajaran.List;

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
import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.presenter.DataMataPelajaranListPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.presenter.IDataMataPelajaranListPresenter;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstarsapp.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataMataPelajaranListActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranListView {

    IDataMataPelajaranListPresenter dataMataPelajaranListPresenter;

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
        setContentView(R.layout.activity_data_mata_pelajaran_list);

        dataMataPelajaranListPresenter = new DataMataPelajaranListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        dataMataPelajaranListPresenter.onLoadDataList();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataMataPelajaranListPresenter.onLoadDataList();

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("home->view->editMataPelajaran")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
//            startActivity(new Intent(getApplicationContext(), DataMataPelajaranAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        AdapterDataMataPelajaranList adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataMataPelajaranList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editMataPelajaran")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataMataPelajaranList.setOnItemClickListener((view, position) -> {
            Intent intent;
            if (statusActivity.equals("home->view->editMataPelajaran")) {
//                    intent = new Intent(getApplicationContext(), DataMataPelajaranEditActivity.class);
//                    intent.putExtra(DataMataPelajaranEditActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
//                    intent.putExtra(DataMataPelajaranEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
//                    startActivity(intent);
            } else if (statusActivity.equals("xx->view->yy")) {

            }
        });
    }

    @Override
    public void showDialogDelete(String kode, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Data " + nama + " ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", (dialog, id) -> {

                    try {
                        dataMataPelajaranListPresenter.onDelete(kode);
                    } catch (Exception e) {
                        globalProcess.onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
                    }

                })
                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());

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
        dataMataPelajaranListPresenter.onLoadDataList();
    }
}
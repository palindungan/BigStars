package com.its.bigstarsapp.Activities.Data.Pengajar.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstarsapp.Activities.Data.Murid.List.presenter.DataMuridListPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.Add.DataPengajarAddActivity;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter.DataPengajarListPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.presenter.IDataPengajarListPresenter;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.view.IDataPengajarListView;
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

    }

    @Override
    public void showDialogDelete(String kode, String nama) {

    }
}
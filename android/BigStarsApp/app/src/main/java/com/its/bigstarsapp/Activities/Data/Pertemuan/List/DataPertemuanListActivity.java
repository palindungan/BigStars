package com.its.bigstarsapp.Activities.Data.Pertemuan.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.its.bigstarsapp.Activities.Data.Pertemuan.List.presenter.DataPertemuanListPresenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.presenter.IDataPertemuanListPresenter;
import com.its.bigstarsapp.Activities.Data.Pertemuan.List.view.IDataPertemuanListView;
import com.its.bigstarsapp.Adapters.AdapterDataPertemuanList;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

public class DataPertemuanListActivity extends AppCompatActivity implements View.OnClickListener, IDataPertemuanListView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    IDataPertemuanListPresenter dataPertemuanListPresenter;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String id_pengajar;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pertemuan_list);

        dataPertemuanListPresenter = new DataPertemuanListPresenter(this, this);

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(this);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        globalProcess.initActionBar(toolbar);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        statusActivity = sessionManager.getStatusActivity();

        dataPertemuanListPresenter.onLoadDataList("" + id_pengajar);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Your code to make your refresh action
            dataPertemuanListPresenter.onLoadDataList("" + id_pengajar);

            // CallYourRefreshingMethod();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {
        AdapterDataPertemuanList adapterDataPertemuanList = new AdapterDataPertemuanList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataPertemuanList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataPertemuanList.notifyDataSetChanged();

//        adapterDataPertemuanList.setOnItemClickListener((view, position) -> {
//            Intent intent;
//            if (statusActivity.equals("")) {
//
//            }
//        });
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
        dataPertemuanListPresenter.onLoadDataList("" + id_pengajar);
    }
}